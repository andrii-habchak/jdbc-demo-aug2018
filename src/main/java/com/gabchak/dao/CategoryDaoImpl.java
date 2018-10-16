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
    public Category findById(long id) {
        return super.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return super.findAll();
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
            while (!resultSet.isAfterLast()){
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
        return null;
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(PreparedStatement preparedStatement, Category category) {
        return null;
    }

}
