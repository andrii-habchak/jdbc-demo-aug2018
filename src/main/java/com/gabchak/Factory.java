package com.gabchak;

import com.gabchak.controller.GetAllCategoriesController;
import com.gabchak.controller.PageNotFoundController;
import com.gabchak.controller.RegisterController;
import com.gabchak.dao.CategoryDaoImpl;
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
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
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

    public static RegisterController getRegisterController() {
        return new RegisterController();
    }

    public static UserServiceImpl getUserServiceImpl() {
        return new UserServiceImpl();
    }
}
