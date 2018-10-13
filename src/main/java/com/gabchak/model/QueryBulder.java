package com.gabchak.model;

import com.gabchak.metaData.ColumnName;
import com.gabchak.metaData.TableName;

import java.lang.reflect.Field;

public class QueryBulder {

    private int questionCountForInsertQuery;

    public String getSelectByIdQuery(Class<?> inputClass) {
        return "SELECT * FROM " + getTableName(inputClass) + " WHERE " + getIdFieldName(inputClass) + " = ?;";
    }

    public String getDeleteByIdQuery(Class<?> inputClass) {
        return "DELETE " + getTableName(inputClass) + " WHERE " + getIdFieldName(inputClass) + " = ?;";
    }

    public String getUpdateQuery(Class<?> inputClass) {
        return "UPDATE " + getTableName(inputClass) + " SET " + getValuesForUpdate(inputClass) + " WHERE " + getIdFieldName(inputClass) + " = ?;";
    }

    public String getInsertQuery(Class<?> inputClass) {
        return "INSERT INTO " + getTableName(inputClass) + "(" + getColumnsNameForInsert(inputClass) + ")" +
                " VALUES " + "(" + getQuestionMarks(inputClass) + ")" + " WHERE " + getIdFieldName(inputClass) + " = ?;";
    }

    public String getSelectAllQuery(Class<?> inputClass) {
        return "SELECT * FROM " + getTableName(inputClass);
    }

    private String getTableName(Class<?> inputClass) {
        if (inputClass.isAnnotationPresent(TableName.class)) {
            TableName tableName = inputClass.getAnnotation(TableName.class);
            return tableName.value().toUpperCase();
        }
        return "No Table Annotation";
    }

    private String getIdFieldName(Class<?> inputClass) {
        Field[] allFields = inputClass.getDeclaredFields();
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ColumnName.class) && field.getName().contains("id")) {
                return field.getName().toUpperCase();
            }
        }
        return "No ID";
    }

    private String getValuesForUpdate(Class<?> inputClass) {
        Field[] allFields = inputClass.getDeclaredFields();
        StringBuilder query = new StringBuilder();
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ColumnName.class) && !field.getName().contains("id")) {
                query.append(field.getName());
                query.append(" = ?, ");
            }
        }
        query.setLength(query.length() - 2);

        return query.toString().toUpperCase();
    }

    private String getColumnsNameForInsert(Class<?> inputClass) {
        Field[] allFields = inputClass.getDeclaredFields();
        StringBuilder query = new StringBuilder();
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ColumnName.class) && !field.getName().contains("id")) {
                query.append(field.getName());
                query.append(", ");
                questionCountForInsertQuery++;
            }
        }
        query.setLength(query.length() - 2);
        return query.toString().toUpperCase();
    }

    private String getQuestionMarks(Class<?> inputClass) {
        StringBuilder query = new StringBuilder();
        for (int i = 0; i < questionCountForInsertQuery; i++) {
            query.append("?, ");
        }
        if (questionCountForInsertQuery != 0) {
            query.setLength(query.length() - 2);
        }
        return query.toString();
    }

}
