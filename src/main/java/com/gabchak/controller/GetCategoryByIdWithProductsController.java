package com.gabchak.controller;

import com.gabchak.model.Category;
import com.gabchak.service.CategoryService;
import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

public class GetCategoryByIdWithProductsController implements Controller {
    private final CategoryService categoryService;

    public GetCategoryByIdWithProductsController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        Category category = categoryService.findByIdWithProductsList(getIdFromRequest(request));
        ViewModel vm = ViewModel.of("category");
        vm.addAttribute("category", category);
        return vm;
    }

    private Long getIdFromRequest(Request request) {
        String idObject = request.getParamByName("c_id");
        return Long.valueOf(idObject);
    }
}
