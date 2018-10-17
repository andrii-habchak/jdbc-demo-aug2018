package com.gabchak.service;

import com.gabchak.dao.ProductDao;
import com.gabchak.model.Category;
import com.gabchak.model.Product;

import java.util.List;

public class ProductServiceImpl extends AbstractService<Product, Long> implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        super(productDao);
        this.productDao = productDao;
    }

    @Override
    public void insert(Product product) {
        super.insert(product);
    }

    @Override
    public Product findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void update(Product product) {
        super.update(product);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return super.findAll();
    }

    @Override
    public Product findByName(String name) {
        return productDao.findByName(name);
    }

}
