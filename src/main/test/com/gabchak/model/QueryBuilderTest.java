package com.gabchak.model;

import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;

public class QueryBuilderTest {

    private static QueryBuilder queryBuilder;

    @BeforeClass
    public static void setUp() {
        queryBuilder = new QueryBuilder();
    }

    @org.junit.Test
    public void categoryGetSelectByIdQuery() {
        String expected = "SELECT * FROM CATEGORIES WHERE ID = ?;";
        String actually = queryBuilder.getSelectByIdQuery(Category.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void categoryGetDeleteByIdQuery() {
        String expected = "DELETE CATEGORIES WHERE ID = ?;";
        String actually = queryBuilder.getDeleteByIdQuery(Category.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void categoryGetUpdateQuery() {
        String expected = "UPDATE CATEGORIES SET CATEGORY_NAME = ? WHERE ID = ?;";
        String actually = queryBuilder.getUpdateQuery(Category.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void categoryGetInsertQuery() {
        String expected = "INSERT INTO CATEGORIES (CATEGORY_NAME)" +
                " VALUES (?) WHERE ID = ?;";
        String actually = queryBuilder.getInsertQuery(Category.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void categoryGetSelectAllQuery() {
        String expected = "SELECT * FROM CATEGORIES";
        String actually = queryBuilder.getSelectAllQuery(Category.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void productGetSelectByIdQuery() {
        String expected = "SELECT * FROM PRODUCTS WHERE ID = ?;";
        String actually = queryBuilder.getSelectByIdQuery(Product.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void productGetDeleteByIdQuery() {
        String expected = "DELETE PRODUCTS WHERE ID = ?;";
        String actually = queryBuilder.getDeleteByIdQuery(Product.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void productGetUpdateQuery() {
        String expected = "UPDATE PRODUCTS SET NAME = ?, PRICE = ?, DESCRIPTION = ?, FK_CATEGORIES = ? WHERE ID = ?;";
        String actually = queryBuilder.getUpdateQuery(Product.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void productGetInsertQuery() {
        String expected = "INSERT INTO PRODUCTS (NAME, PRICE, DESCRIPTION, FK_CATEGORIES)" +
                " VALUES (?, ?, ?, ?) WHERE ID = ?;";
        String actually = queryBuilder.getInsertQuery(Product.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void productGetSelectAllQuery() {
        String expected = "SELECT * FROM PRODUCTS";
        String actually = queryBuilder.getSelectAllQuery(Product.class);
        assertEquals(expected, actually);
    }

}
