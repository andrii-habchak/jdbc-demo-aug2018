package com.gabchak.service;

import com.gabchak.model.Product;

import java.util.List;

public interface ProductService extends Service<Product, Long> {

    Product findByName(String name);
}
