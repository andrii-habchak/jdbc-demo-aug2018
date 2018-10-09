package com.gabchak.dao;

import java.util.List;

public interface GenericDao<T, ID> {

    public void create(T t);
    public T findById(ID id);
    public int update(T t);
    public void delete(ID id);
    public List<T> readAll();
}
