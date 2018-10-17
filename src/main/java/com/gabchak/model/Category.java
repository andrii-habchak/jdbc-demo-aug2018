package com.gabchak.model;

import com.gabchak.metaData.ColumnName;
import com.gabchak.metaData.TableName;

import java.util.List;

@TableName("CATEGORIES")
public class Category {

    @ColumnName
    private Long id;
    @ColumnName
    private String name;
    private List<Product> products;

    public Category(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
