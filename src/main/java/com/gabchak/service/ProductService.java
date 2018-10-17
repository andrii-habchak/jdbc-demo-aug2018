package com.gabchak.service;

import com.gabchak.model.Product;

import java.util.List;

public interface ProductService extends Service {

    Product findByName(String name);
}
