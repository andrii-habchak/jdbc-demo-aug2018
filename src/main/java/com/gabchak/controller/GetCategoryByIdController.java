package com.gabchak.controller;

import com.gabchak.dao.CategoryDao;
import com.gabchak.model.Category;
import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

public class GetCategoryByIdController implements Controller {
    private final CategoryDao categoryDao;

    public GetCategoryByIdController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public ViewModel process(Request request) {
        Category category = categoryDao.findById(getIdFromRequest(request));
        ViewModel vm = ViewModel.of("category");
        vm.addAttribute("category", category);
        return vm;
    }

    private Long getIdFromRequest(Request request) {
        String idObject = request.getParamByName("c_id");
        return Long.valueOf(idObject);
    }
}
