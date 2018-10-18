package com.gabchak.web.filters;

import com.gabchak.dao.UserDao;
import com.gabchak.model.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.gabchak.Factory.getUserDao;
import static com.gabchak.web.filters.UserFilter.getCookieName;

public class LoginRegisterFilter implements Filter {

    private final Set<String> uriSet = new HashSet<>();
    private UserDao userDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = getUserDao();
        uriSet.add("/servlet/login");
        uriSet.add("/servlet/register");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Cookie[] cookies = req.getCookies();
        String token = null;
        User user = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(getCookieName())) {
                token = cookie.getValue();
            }
        }

        if (token != null) {
            user = userDao.findByToken(token);
        }

        if (user != null && uriSet.contains(req.getRequestURI())) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
