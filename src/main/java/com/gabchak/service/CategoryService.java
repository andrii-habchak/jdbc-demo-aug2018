package com.gabchak.service;

import com.gabchak.model.Category;

public interface CategoryService extends Service<Category, Long> {

    Category findByName(String name);
}
