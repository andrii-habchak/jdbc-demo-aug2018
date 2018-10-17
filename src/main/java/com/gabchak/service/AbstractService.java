package com.gabchak.service;

import com.gabchak.dao.GenericDao;
import com.gabchak.model.Category;

import java.util.List;

public abstract class AbstractService<T, ID> implements Service<T, ID> {

    private GenericDao genericDao;

    public AbstractService(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public void insert(Category category) {
        genericDao.insert(category);
    }

    public T findById(ID id) {
        return (T) genericDao.findById(id);
    }

    public void update(T t) {
        genericDao.update(t);
    }

    public void deleteById(ID id) {
        genericDao.deleteById(id);
    }

    public List<T> findAll() {
        return genericDao.findAll();
    }
}
