package com.gabchak.controller;

import com.gabchak.model.Category;
import com.gabchak.service.CategoryService;
import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

import java.util.List;

public class GetAllCategoriesController implements Controller{

    private final CategoryService categoryService;

    public GetAllCategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        List<Category> categories = categoryService.findAll();
        ViewModel vm = ViewModel.of("categories");
        vm.addAttribute("categories", categories);
        return vm;
    }


}
