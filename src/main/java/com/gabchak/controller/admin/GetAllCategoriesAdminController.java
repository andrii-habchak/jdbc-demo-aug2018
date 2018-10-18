package com.gabchak.controller.admin;

import com.gabchak.controller.Controller;
import com.gabchak.dao.CategoryDao;
import com.gabchak.model.Category;
import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

import java.util.List;

public class GetAllCategoriesAdminController implements Controller{

    private final CategoryDao categoryDao;

    public GetAllCategoriesAdminController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public ViewModel process(Request request) {
        List<Category> categories = categoryDao.findAll();
        ViewModel vm = ViewModel.of("manageCategories");
        vm.addAttribute("categories", categories);
        return vm;
    }


}
