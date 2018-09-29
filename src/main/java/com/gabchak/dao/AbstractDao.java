package com.gabchak.dao;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDao<T, ID> implements GenericDao<T, ID> {

    protected final Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public T create(T t) {
        return null;
    }

    @Override
    public T read(ID id) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public void delete(ID id) {

    }

    @Override
    public List<T> readAll() {
        return null;
    }
}

