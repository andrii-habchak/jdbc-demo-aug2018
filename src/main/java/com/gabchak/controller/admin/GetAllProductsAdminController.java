package com.gabchak.controller.admin;

import com.gabchak.controller.Controller;
import com.gabchak.dao.CategoryDao;
import com.gabchak.model.Category;
import com.gabchak.model.Product;
import com.gabchak.service.ProductService;
import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

import java.util.List;

public class GetAllProductsAdminController implements Controller{

    private ProductService productService;

    public GetAllProductsAdminController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request) {
        List<Product> products = productService.findAll();
        ViewModel vm = ViewModel.of("manageProducts");
        vm.addAttribute("products", products);
        return vm;
    }


}
