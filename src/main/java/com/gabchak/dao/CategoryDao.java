package com.gabchak.dao;

import com.gabchak.model.Category;

public interface CategoryDao {
    Category findById(long id);
}
