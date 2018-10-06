package com.gabchak.dao;

import java.util.List;

public interface GenericDao<T, ID> {

    public void create(T t);
    public T read(ID id);
    public int update(T t);
    public void delete(ID id);
    public List<T> readAll();
}
