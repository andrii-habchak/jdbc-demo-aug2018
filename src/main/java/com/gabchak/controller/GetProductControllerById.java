package com.gabchak.controller;

import com.gabchak.model.Product;
import com.gabchak.service.ProductService;
import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

public class GetProductControllerById implements Controller {

    private final ProductService productService;

    public GetProductControllerById(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request) {
        Product product = productService.findById(getIdFromRequest(request));
        ViewModel vm = ViewModel.of("product");
        vm.addAttribute("product", product);
        return vm;
    }

    private Long getIdFromRequest(Request request) {
        String idObject = request.getParamByName("p_id");
        return Long.valueOf(idObject);
    }
}
