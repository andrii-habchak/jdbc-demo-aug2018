package com.gabchak.service;

import com.gabchak.dao.CategoryDao;
import com.gabchak.model.Category;

import java.util.List;

public class CategoryServiceImpl extends AbstractService<Category, Long> implements CategoryService {
    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }

    @Override
    public Category findByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
    public void insert(Category category) {
        super.insert(category);
    }

    @Override
    public Category findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public void update(Category category) {
        super.update(category);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public List<Category> findAll() {
        return super.findAll();
    }
}
