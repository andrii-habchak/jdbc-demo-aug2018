package com.gabchak.dao;

import com.gabchak.model.QueryBulder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T, ID> implements GenericDao<T, ID> {

    protected final Connection connection;
    private QueryBulder queryBulder = new QueryBulder();

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public T findById(ID id) {
        String query = queryBulder.getSelectByIdQuery(connection.getClass());
        PreparedStatement preparedStatement;
        T result = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = resultSet.next() ? getObjectFromResultSet(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int update(T t) {
        String query = queryBulder.getInsertQuery(connection.getClass());
        PreparedStatement preparedStatement;
        int result = 0;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement = prepareStatementForUpdate(preparedStatement, t);

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void deleteById(ID id) {
        String query = queryBulder.getDeleteByIdQuery(connection.getClass());
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(T t) {

    }

    @Override
    public List<T> findAll() {
        return null;
    }

    protected abstract T getObjectFromResultSet(ResultSet resultSet);

    protected abstract PreparedStatement prepareStatementForUpdate(PreparedStatement preparedStatement, T t);
}

