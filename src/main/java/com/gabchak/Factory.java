package com.gabchak;

import com.gabchak.controller.*;
import com.gabchak.dao.*;
import com.gabchak.model.QueryBuilder;
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
        return new GetCategoryByIdController(getCategoryService());
    }

    public static GetAllCategoriesController getAllCategoriesController() {
        return  new GetAllCategoriesController(getCategoryService());
    }

    public static Controller getLoginPageController() {
        return new LoginController(getUserService());
    }

    public static Controller getLogoutController() {
        return new LogoutController();
    }

    public static Controller getRegisterController() {
        return new RegisterController(getUserService());
    }

    public static Controller getProductByIdController() {
        return new GetProductControllerById(getProductService());
    }

    public static Controller getPageNotFoundController() {
        return new  PageNotFoundController();
    }



    public static CategoryService getCategoryService() {
        return new CategoryServiceImpl(getCategoryDao(getConnection()));
    }

    public static UserService getUserService() {
        return new UserServiceImpl(getUserDao(getConnection()));
    }

    public static ProductService getProductService() {
        return new ProductServiceImpl(getProductDao(getConnection()));
    }


    public static QueryBuilder getQueryBuilder() {
        return new QueryBuilder();
    }


    private static CategoryDao getCategoryDao(Connection connection) {
        return new CategoryDaoImpl(connection);
    }

    private static UserDao getUserDao(Connection connection) {
        return new UserDaoImpl(connection);
    }

    private static ProductDao getProductDao(Connection connection) {
        return new ProductDaoImpl(connection);
    }
}
