package com.gabchak.dao;

import com.gabchak.model.Category;

import java.util.List;

public interface CategoryDao {
    Category findById(long id);
    List<Category> findAll();
}
