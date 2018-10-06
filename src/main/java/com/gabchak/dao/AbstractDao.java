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


    @Override
    public void create(T t) {

    }

    //create annotation for fields/methods
    //use metadata for extract table name and column name
    //rename method
    @Override
    public T findById(ID id) {
        String query = "SELECT * FROM ? WHERE ID = ?";
        PreparedStatement preparedStatement;
        T result;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = resultSet.next() ? getObjectFromResultSet(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract T getObjectFromResultSet(ResultSet resultSet); //Need implementation

    @Override
    public int update(T t) {
        return 0;
    }

    @Override
    public void delete(ID id) {

    }

    @Override
    public List<T> readAll() {
        return null;
    }
}

