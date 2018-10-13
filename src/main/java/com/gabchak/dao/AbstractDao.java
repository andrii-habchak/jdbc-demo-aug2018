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
    public void insert(T t) {

    }

    //insert annotation for fields/methods
    //use metadata for extract table name and column name
    //rename method
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

    protected abstract T getObjectFromResultSet(ResultSet resultSet); //Need implementation

    @Override
    public int update(T t) {
        return 0;
    }

    @Override
    public void deleteById(ID id) {

    }

    @Override
    public List<T> findAll() {
        return null;
    }

    abstract String createQuery(); //Собираем поля в query
}

