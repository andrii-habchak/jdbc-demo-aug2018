package com.gabchak.dao;

import java.util.List;

public interface GenericDao<T, ID> {

    void insert(T t);
    T findById(ID id);
    void update(T t);
    void deleteById(ID id);
    List<T> findAll();
}
