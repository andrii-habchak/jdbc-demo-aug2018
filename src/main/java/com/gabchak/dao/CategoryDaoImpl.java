package com.gabchak.dao;

import com.gabchak.model.Category;
import com.gabchak.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {
    public CategoryDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Category findById(long id) {
        String query = "SELECT C.ID, C.CATEGORY_NAME, P.ID, P.NAME, P.PRICE, P.DESCRIPTION FROM CATEGORIES"
                + "JOIN PRODUCTS P ON C.ID = P.FK_CATEGORIES"
                + "WHERE C.ID = ?";

        PreparedStatement statement;
        ResultSet resultSet;
        Category result = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            result = resultSet.next() ? getCategory(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Category getCategory(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();

        while (!resultSet.isAfterLast()) { //resultSet.next() не будет работать. Почему?
            Product product = new Product(
                    resultSet.getLong(3), //Column index of select from DB
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)
            );
            products.add(product);
            resultSet.next();
        }

        return new Category(
                resultSet.getLong(1),
                resultSet.getString(2),
                products);
    }
}
