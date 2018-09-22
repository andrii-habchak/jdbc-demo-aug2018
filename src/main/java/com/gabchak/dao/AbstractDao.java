package com.gabchak.dao;

import java.sql.Connection;

public class AbstractDao<T> {

    final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    //TODO:CRUD

}
