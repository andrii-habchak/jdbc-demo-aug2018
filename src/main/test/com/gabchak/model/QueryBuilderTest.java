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
    public void ProductGetSelectByIdQuery() {
        String expected = "SELECT * FROM PRODUCTS WHERE ID = ?;";
        String actually = queryBuilder.getSelectByIdQuery(Product.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void ProductGetDeleteByIdQuery() {
        String expected = "DELETE PRODUCTS WHERE ID = ?;";
        String actually = queryBuilder.getDeleteByIdQuery(Product.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void ProductGetUpdateQuery() {
        String expected = "UPDATE PRODUCTS SET NAME = ?, PRICE = ?, DESCRIPTION = ?, FK_CATEGORIES = ? WHERE ID = ?;";
        String actually = queryBuilder.getUpdateQuery(Product.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void ProductGetInsertQuery() {
        String expected = "INSERT INTO PRODUCTS (NAME, PRICE, DESCRIPTION, FK_CATEGORIES)" +
                " VALUES (?, ?, ?, ?) WHERE ID = ?;";
        String actually = queryBuilder.getInsertQuery(Product.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void ProductGetSelectAllQuery() {
        String expected = "SELECT * FROM PRODUCTS";
        String actually = queryBuilder.getSelectAllQuery(Product.class);
        assertEquals(expected, actually);
    }

}
