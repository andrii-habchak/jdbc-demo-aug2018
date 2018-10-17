package com.gabchak.dao;

import com.gabchak.model.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product, Long> {

    Product findByName(String name);
}
