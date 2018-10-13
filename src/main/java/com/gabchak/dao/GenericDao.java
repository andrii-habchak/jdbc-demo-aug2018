package com.gabchak.dao;

import java.util.List;

public interface GenericDao<T, ID> {

    public void insert(T t);
    public T findById(ID id);
    public int update(T t);
    public void deleteById(ID id);
    public List<T> findAll();
}
