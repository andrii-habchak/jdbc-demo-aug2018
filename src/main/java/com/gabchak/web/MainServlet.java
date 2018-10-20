package com.gabchak.web;

import com.gabchak.Factory;
import com.gabchak.controller.Controller;
import com.gabchak.controller.admin.AddProductController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.gabchak.Factory.*;

public class MainServlet extends HttpServlet {

    private final static Map<Request, Controller> controllerMap = new HashMap<>();

    static {
        controllerMap.put(Request.of("GET", "/servlet/categories"), Factory.getAllCategoriesController());
        controllerMap.put(Request.of("GET", "/servlet/category"), Factory.getGetCategoryByIdController());
        controllerMap.put(Request.of("GET", "/servlet/login"), r -> ViewModel.of("login"));
        controllerMap.put(Request.of("GET", "/servlet/register"), r -> ViewModel.of("register"));
        controllerMap.put(Request.of("POST", "/servlet/register"), Factory.getRegisterController());
        controllerMap.put(Request.of("GET", "/servlet/home"), r -> ViewModel.of("home"));
        controllerMap.put(Request.of("GET", "/servlet/admin"), r -> ViewModel.of("adminPage"));
        controllerMap.put(Request.of("GET", "/servlet/admin/content"), r -> ViewModel.of("content"));
        controllerMap.put(Request.of("GET", "/servlet/admin/categories"), getAllCategoriesAdminController("manageCategories"));
        controllerMap.put(Request.of("GET", "/servlet/admin/products"), getAllProductsAdminController());
        controllerMap.put(Request.of("GET", "/servlet/admin/add-product"), r -> ViewModel.of("addProduct"));
        controllerMap.put(Request.of("GET", "/servlet/admin/add-product"), getAllCategoriesAdminController("addProduct"));
        controllerMap.put(Request.of("POST", "/servlet/admin/add-product"), getAddProductController());
        controllerMap.put(Request.of("POST", "/servlet/login"), Factory.getLoginPageController());
        controllerMap.put(Request.of("GET", "/servlet/product"), Factory.getProductByIdController());
        controllerMap.put(Request.of("GET", "/servlet/logout"), Factory.getLogoutController());
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
        Request request = Request.of(req.getMethod(), req.getRequestURI(), req.getParameterMap(), req.getCookies());

        Controller controller = controllerMap.getOrDefault(request, getPageNotFoundController());

        ViewModel vm = controller.process(request);

        sendResponse(vm, req, resp);
    }

    private void sendResponse(ViewModel vm, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectUrl = "/WEB-INF/views/%s.jsp";
        vm.getModel().forEach(req::setAttribute); // (k, v) -> req.setAttribute(k, v)
        addCookie(vm, resp);
        req.getRequestDispatcher(String.format(redirectUrl, vm.getView())).forward(req,resp);
    }

    private void addCookie(ViewModel vm, HttpServletResponse resp) {
        if (vm.getCookie() != null) {
            resp.addCookie(vm.getCookie());
        }
    }
}
