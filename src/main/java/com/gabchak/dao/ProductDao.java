package com.gabchak.dao;

import com.gabchak.model.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product, Long> {

    void insert(Product product);

    void update(Product product);

    Product findByName(String name);

    Product findById(Long id);

    List<Product> findAll();
}
