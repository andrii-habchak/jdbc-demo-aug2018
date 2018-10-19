package com.gabchak.dao;

import com.gabchak.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends AbstractDao<Product, Long> implements ProductDao {

    public ProductDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Product product) {
        super.insert(product);
    }

    public Product findByName(String name) {
        String query = "SELECT ID, NAME, PRICE, DESCRIPTION FROM PRODUCTS WHERE NAME = ?";
        return super.getObjectByParam(query, name);
    }

    @Override
    public Product findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public List<Product> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Product product) {
        super.update(product);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    protected Product getObjectFromResultSet(ResultSet resultSet) {
        try {
            return new Product(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(PreparedStatement preparedStatement, Product product) {
        try {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setLong(4, product.getCategory().getId());
            preparedStatement.setLong(5, product.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(PreparedStatement preparedStatement, Product product) {
        try {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setLong(4, product.getCategory().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

}
