package com.gabchak.web;

import com.gabchak.Factory;
import com.gabchak.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.gabchak.Factory.getPageNotFoundController;

public class MainServlet extends HttpServlet {

    private final static Map<Requeast, Controller> controllerMap = new HashMap<>();

    static {
        controllerMap.put(Requeast.of("GET", "/servlet/categories"), Factory.getAllCategoriesController());
        controllerMap.put(Requeast.of("GET", "/servlet/login"), r -> ViewModel.of("login"));
        controllerMap.put(Requeast.of("GET", "/servlet/register"), r -> ViewModel.of("register"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Requeast requeast = Requeast.of(req.getMethod(), req.getRequestURI());

        Controller controller = controllerMap.getOrDefault(requeast, getPageNotFoundController());

        ViewModel vm = controller.process(requeast);

        sendResponse(vm, req, resp);


//        if (req.getMethod().equals("GET") && req.getRequestURI().equals("/servlet/home")) {
//            req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req,resp);
//        } else {
//            req.getRequestDispatcher("/WEB-INF/views/404.jsp").forward(req,resp);
//        }
    }

    private void sendResponse(ViewModel vm, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectUrl = "/WEB-INF/views/%s.jsp";
        vm.getModel().forEach(req::setAttribute);
        req.getRequestDispatcher(String.format(redirectUrl, vm.getView())).forward(req,resp);
    }
}
//D:\Users\User\Documents\apache-tomcat-8.5.34
