package com.gabchak.service;

import java.util.List;

public interface Service<T, ID> {

    void insert(T t);
    T findById(ID id);
    void update(T t);
    void deleteById(ID id);
    List<T> findAll();
}
