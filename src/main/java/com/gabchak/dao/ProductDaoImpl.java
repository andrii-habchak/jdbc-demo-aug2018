package com.gabchak.dao;

import com.gabchak.Factory;
import com.gabchak.model.Category;
import com.gabchak.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends AbstractDao<Product, Integer> implements ProductDao {

    public ProductDaoImpl(Connection connection) {
        super(connection);
    }

    public void save(Product product) {
        String query = "INSERT INTO PRODUCTS (NAME, PRICE, DESCRIPTION) VALUES (?, ?, ?);";

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product findByName(String name) {
        String query = "SELECT ID, NAME, PRICE, DESCRIPTION FROM PRODUCTS WHERE NAME = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "iPhone");
            resultSet = preparedStatement.executeQuery();
            product = resultSet.next() ? getProductFromResultSet(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> findAll() {
        String query = "SELECT ID, NAME, PRICE, DESCRIPTION FROM PRODUCTS";
        List<Product> result = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet;

        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getDouble(3),
                resultSet.getString(4)
        );
    }

    public static void main(String[] args) {
/*
        ProductDaoImpl dao = new ProductDaoImpl(Factory.getConnection());
        Product product1 = new Product("SAMSUNG", 699.0, "SAMSUNG PHONE");
        Product product2 = new Product("iMac", 4699.0, "Expensive environment");
        Product product3 = new Product("Shoes", 25.0, "Brown Shoes");

        dao.save(product1);
        dao.save(product2);
        dao.save(product3);

        List<Product> result = dao.findAll();
        System.out.println(result);
*/
        CategoryDao categoryDao = new CategoryDaoImpl(Factory.getConnection());

        Category category = categoryDao.findById(1L);
        System.out.println(category);
    }
}
