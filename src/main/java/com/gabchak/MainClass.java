package com.gabchak;

import com.gabchak.dao.CategoryDaoImpl;
import com.gabchak.model.Category;

public class MainClass {

    public static void main(String[] args) {
        CategoryDaoImpl categoryDao = new CategoryDaoImpl(Factory.getConnection());
        Category category = categoryDao.findById(1L);
        System.out.println(category);

    }
}
