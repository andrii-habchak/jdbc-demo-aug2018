package com.gabchak.web;

import com.gabchak.Factory;
import com.gabchak.dao.UserDao;
import com.gabchak.model.Role;
import com.gabchak.model.Role.RoleName;
import com.gabchak.model.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.gabchak.model.Role.RoleName.ADMIN;
import static com.gabchak.model.Role.RoleName.USER;

public class UserFilter implements Filter {

    private final Map<String, RoleName> protectedUriMap = new HashMap<>();
    private UserDao userDao;
    private String COOKIE_NAME = "MATE";

    @Override
    public void init(FilterConfig filterConfig) {
        userDao = Factory.getUserDao();
        protectedUriMap.put("servlet/categories", USER);
        protectedUriMap.put("servlet/category", USER);
        protectedUriMap.put("servlet/products", USER);
        protectedUriMap.put("servlet/product", USER);
        protectedUriMap.put("servlet/home", USER);
        protectedUriMap.put("servlet/admin", ADMIN);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        RoleName roleName = protectedUriMap.get(request.getRequestURI());
        String token = null;
        User user;

        if (roleName == null) {
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
                    if (verifyRole(user, roleName)) {
                        request.setAttribute("user_id", user.getId());
                        processAuthenticated(servletRequest, servletResponse, filterChain);
                    } else {
                        processAccessDenied(servletRequest, servletResponse, filterChain);
                    }
                }
            }
        }

    }

    private void processAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        servletRequest.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(servletRequest, servletResponse);
    }

    private boolean verifyRole(User user, RoleName roleName) {
        return user.getRoles().stream()
                .map(Role::getRoleName)
                .filter(r -> r.equals(roleName))
                .anyMatch(r -> r.equals(roleName));
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
