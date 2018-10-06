package com.gabchak.web;

import com.gabchak.Factory;
import com.gabchak.dao.UserDao;
import com.gabchak.model.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UserFilter implements Filter {

    private final Set<String> protectedUriSet = new HashSet<>();
    private UserDao userDao;
    private String COOKIE_NAME = "MATE";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = Factory.getUserDao();
        protectedUriSet.add("servlet/categories");
        protectedUriSet.add("servlet/category");
        protectedUriSet.add("servlet/products");
        protectedUriSet.add("servlet/product");
        protectedUriSet.add("servlet/home");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        String token = null;
        User user = null;

        if (!protectedUriSet.contains(request.getRequestURI())) {
            processAuthenticated(servletRequest, servletResponse, filterChain);
            return;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(COOKIE_NAME)) {
                token = cookie.getValue();
            }
            if (token == null) {
                processUnauthenticated(servletRequest, servletResponse);
            } else {
                user = userDao.findByToken(token);
                if (user == null) {
                    processUnauthenticated(servletRequest, servletResponse);
                } else {
                    request.setAttribute("user_id", user.getId());
                    processAuthenticated(servletRequest, servletResponse, filterChain);
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }

    }

    private void processAuthenticated(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void processUnauthenticated(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletRequest.getRequestDispatcher("WEB-INF/views/login.jsp").forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
