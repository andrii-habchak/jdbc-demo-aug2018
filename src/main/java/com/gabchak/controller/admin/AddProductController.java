package com.gabchak.controller.admin;

import com.gabchak.controller.Controller;
import com.gabchak.model.Category;
import com.gabchak.model.Product;
import com.gabchak.service.ProductService;
import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

import java.util.List;

public class AddProductController implements Controller {

    private final ProductService productService;

    public AddProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request) {
        String productName = request.getParamByName("productName");
        Double productPrice = Double.valueOf(request.getParamByName("price"));
        String description = request.getParamByName("description");
        Long categoryId = Long.valueOf(request.getParamByName("categoryId"));

        Product product = new Product(productName, productPrice, description);
        Category category = new Category();
        category.setId(categoryId);
        product.setCategory(category);

        productService.save(product);

        List<Product> products = productService.findAll();

        ViewModel vm = ViewModel.of("manageProducts");
        vm.addAttribute("products", products);

        return vm;
    }
}
