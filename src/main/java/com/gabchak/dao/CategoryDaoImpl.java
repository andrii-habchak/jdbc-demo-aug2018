package com.gabchak.dao;

import com.gabchak.model.Category;
import com.gabchak.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends AbstractDao<Category, Long> implements CategoryDao {

    public CategoryDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Category findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Category category) {
        super.update(category);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void insert(Category category) {
        super.insert(category);
    }

    @Override
    public Category findByName(String name) {
        String query = "SELECT * FROM CATEGORIES WHERE CATEGORY_NAME = ?";
        return super.getObjectByParam(query, name);
    }

    @Override
    public Category findByIdWithProductsList(Long id) {
        String query = "SELECT C.ID, C.CATEGORY_NAME, P.ID, P.NAME, P.PRICE, P.DESCRIPTION " +
                "FROM CATEGORIES C JOIN PRODUCTS P " +
                "ON C.ID = P.FK_CATEGORIES " +
                "WHERE C.ID = ?";

        return getObjectByParam(query, id);
    }


    @Override
    protected Category getObjectFromResultSet(ResultSet resultSet) {
        List<Product> products = new ArrayList<>();
        Category result = null;
        try {
            result = new Category(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    products);
            while (!resultSet.isAfterLast()) {
                Product product = new Product(
                        resultSet.getLong(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getString(6));
                products.add(product);
                resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(PreparedStatement preparedStatement, Category category) {
        try {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setLong(2, category.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(PreparedStatement preparedStatement, Category category) {
        try {
            preparedStatement.setString(1, category.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

}
