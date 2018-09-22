package com.gabchak.dao;

import com.gabchak.model.Product;

import java.util.List;

public interface ProductDao {

    void save(Product product);

    Product findByName(String name);

    List<Product> findAll();
}
