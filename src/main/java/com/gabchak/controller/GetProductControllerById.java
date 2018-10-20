package com.gabchak.controller;

import com.gabchak.dao.ProductDao;
import com.gabchak.dao.ProductDaoImpl;
import com.gabchak.model.Product;
import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

public class GetProductControllerById implements Controller {

    private final ProductDao productDao;

    public GetProductControllerById(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ViewModel process(Request request) {
        Product product = productDao.findById(getIdFromRequest(request));
        ViewModel vm = ViewModel.of("product");
        vm.addAttribute("product", product);
        return vm;
    }

    private Long getIdFromRequest(Request request) {
        String idObject = request.getParamByName("p_id");
        return Long.valueOf(idObject);
    }
}
