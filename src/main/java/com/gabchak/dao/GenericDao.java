package com.gabchak.dao;

import java.util.List;

public interface GenericDao<T, ID> {

    public int create(T t);
    public T read(ID id);
    public T update(T t);
    public void delete(ID id);
    public List<T> readAll();
}
