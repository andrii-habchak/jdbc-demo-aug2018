package com.gabchak.dao;

import com.gabchak.model.Category;

import java.util.List;

public interface CategoryDao extends GenericDao<Category, Long> {
    Category findById(long id);
    List<Category> findAll();
    void insert(Category category);
    void update(Category category);
    void deleteById(Long id);
}
