package com.gabchak.controller;

import com.gabchak.dao.CategoryDao;
import com.gabchak.model.Category;
import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

import java.util.List;

public class GetAllCategoriesController implements Controller{

    private final CategoryDao categoryDao;

    public GetAllCategoriesController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public ViewModel process(Request request) {
        List<Category> categories = categoryDao.findAll();
        ViewModel vm = ViewModel.of("categories");
        vm.addAttribute("categories", categories);
        return vm;
    }


}
