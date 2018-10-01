package com.gabchak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T, ID> implements GenericDao<T, ID> {

    protected final Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    abstract String createQuery();
    abstract String updateQuery();
    abstract String deleteQuery();
    abstract String tableName();
    abstract T getObjectFromResultSet(ResultSet resultSet);

    @Override
    public int create(T t) {
        int changedRows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createQuery());
            changedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return changedRows;
    }

    @Override
    public T read(ID id) {
        String query = "SELECT * FROM ? WHERE ID = ?";
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        T result = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tableName());
            preparedStatement.setObject(2, id);
            resultSet = preparedStatement.executeQuery();
            result = resultSet.next() ? getObjectFromResultSet(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public int update(T t) {
        int changedRows = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery());
            changedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return changedRows;
    }

    @Override
    public void delete(ID id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<T> readAll() {
        String query;
        //connection

        return null;
    }
}

