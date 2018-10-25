package com.gabchak.model;

import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;

public class QueryBuilderTest {

    private static QueryBuilder queryBuilder;

    @BeforeClass
    public static void setUp() {
        queryBuilder = new QueryBuilder();
    }

    //Category----------
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
                " VALUES (?);";
        String actually = queryBuilder.getInsertQuery(Category.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void categoryGetSelectAllQuery() {
        String expected = "SELECT * FROM CATEGORIES";
        String actually = queryBuilder.getSelectAllQuery(Category.class);
        assertEquals(expected, actually);
    }

    //Product-----------
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
                " VALUES (?, ?, ?, ?);";
        String actually = queryBuilder.getInsertQuery(Product.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void productGetSelectAllQuery() {
        String expected = "SELECT * FROM PRODUCTS";
        String actually = queryBuilder.getSelectAllQuery(Product.class);
        assertEquals(expected, actually);
    }

    //User-----------------------------
    @org.junit.Test
    public void userGetSelectByIdQuery() {
        String expected = "SELECT * FROM USERS WHERE ID = ?;";
        String actually = queryBuilder.getSelectByIdQuery(User.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void userGetDeleteByIdQuery() {
        String expected = "DELETE USERS WHERE ID = ?;";
        String actually = queryBuilder.getDeleteByIdQuery(User.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void userGetUpdateQuery() {
        String expected = "UPDATE USERS SET EMAIL = ?, PASSWORD = ?, FIRST_NAME = ?, LAST_NAME = ?, TOKEN = ? WHERE ID = ?;";
        String actually = queryBuilder.getUpdateQuery(User.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void userGetInsertQuery() {
        String expected = "INSERT INTO USERS (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, TOKEN)" +
                " VALUES (?, ?, ?, ?, ?);";
        String actually = queryBuilder.getInsertQuery(User.class);
        assertEquals(expected, actually);
    }

    @org.junit.Test
    public void userGetSelectAllQuery() {
        String expected = "SELECT * FROM USERS";
        String actually = queryBuilder.getSelectAllQuery(User.class);
        assertEquals(expected, actually);
    }

}
