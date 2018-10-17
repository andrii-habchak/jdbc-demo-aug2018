package com.gabchak;

import com.gabchak.controller.*;
import com.gabchak.dao.*;
import com.gabchak.service.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {

    static Connection connection = null;

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/java-aug-18", "sa", "");
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Controller getGetCategoryByIdController() {
        return new GetCategoryByIdController(getCategoryService(getCategoryDao(getConnection())));
    }

    public static GetAllCategoriesController getAllCategoriesController() {
        return  new GetAllCategoriesController(getCategoryService(getCategoryDao(getConnection())));
    }

    public static Controller getLoginPageController() {
        return new LoginController(getUserService(getUserDao(getConnection())));
    }

    public static Controller getLogoutController() {
        return new LogoutController();
    }

    public static Controller getRegisterController() {
        return new RegisterController(getUserService(getUserDao(getConnection())));
    }

    public static Controller getProductByIdController() {
        return new GetProductControllerById(getProductService(getProductDao(getConnection())));
    }

    public static Controller getPageNotFoundController() {
        return new  PageNotFoundController();
    }



    public static CategoryService getCategoryService(CategoryDao categoryDao) {
        return new CategoryServiceImpl(categoryDao);
    }

    public static UserService getUserService(UserDao userDao) {
        return new UserServiceImpl(userDao);
    }

    public static ProductService getProductService(ProductDao productDao) {
        return new ProductServiceImpl(productDao);
    }



    public static CategoryDao getCategoryDao(Connection connection) {
        return new CategoryDaoImpl(connection);
    }

    public static UserDao getUserDao(Connection connection) {
        return new UserDaoImpl(connection);
    }

    private static ProductDao getProductDao(Connection connection) {
        return new ProductDaoImpl(connection);
    }
}
