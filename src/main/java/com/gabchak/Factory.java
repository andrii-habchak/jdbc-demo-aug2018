package com.gabchak;

import com.gabchak.controller.*;
import com.gabchak.controller.admin.AddProductController;
import com.gabchak.controller.admin.GetAllCategoriesAdminController;
import com.gabchak.controller.admin.GetAllProductsAdminController;
import com.gabchak.dao.*;
import com.gabchak.service.ProductService;
import com.gabchak.service.ProductServiceImpl;
import com.gabchak.service.UserService;
import com.gabchak.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {

    static Connection connection = null;

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/java-aug-18", "sa", "");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static GetAllCategoriesController getAllCategoriesController() {
        return  new GetAllCategoriesController(getCategoryDaoImpl(getConnection()));
    }

    public static CategoryDaoImpl getCategoryDaoImpl(Connection connection) {
        return new CategoryDaoImpl(connection);
    }

    public static PageNotFoundController getPageNotFoundController() {
        return new  PageNotFoundController();
    }

    public static UserService getUserService() {
        return new UserServiceImpl(getUserDao());
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl(getConnection());
    }

    public static RegisterController getRegisterController() {
        return new RegisterController(getUserService());
    }

    public static UserServiceImpl getUserServiceImpl() {
        return new UserServiceImpl(getUserDao());
    }

    public static Controller getLoginPageController() {
        return new LoginController(getUserService());
    }

    public static Controller getGetCategoryByIdController() {
        return new GetCategoryByIdController(getCategoryDaoImpl(getConnection()));
    }

    public static Controller getProductByIdController() {
        return new GetProductControllerById(getProductDao());
    }

    private static ProductDao getProductDao() {
        return new ProductDaoImpl(connection);
    }

    public static Controller getLogoutController() {
        return new LogoutController();
    }

    public static Controller getAllCategoriesAdminController(String viewName) {
        return new GetAllCategoriesAdminController(getCategoryDaoImpl(getConnection()), viewName);
    }

    public static ProductService getProductService() {
        return new ProductServiceImpl(getProductDao());
    }

    public static Controller getAllProductsAdminController() {
        return new GetAllProductsAdminController(getProductService());
    }

    public static Controller getAddProductController() {
        return new AddProductController(getProductService());
    }
}
