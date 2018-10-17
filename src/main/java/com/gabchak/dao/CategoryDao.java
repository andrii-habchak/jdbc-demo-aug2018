package com.gabchak.dao;

import com.gabchak.model.Category;

import java.util.List;

public interface CategoryDao extends GenericDao<Category, Long> {

    Category findByName(String name);

}
