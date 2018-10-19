package com.gabchak.model;

import com.gabchak.metaData.ColumnName;
import com.gabchak.metaData.TableName;

import java.lang.reflect.Field;

public class QueryBuilder {

    private int count;

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
        return "INSERT INTO " + getTableName(inputClass) + " (" + getColumnsNameForInsert(inputClass) + ")" +
                " VALUES " + "(" + getQuestionMarks() + ")" + " WHERE " + getIdFieldName(inputClass) + " = ?;";
    }

    public String getSelectAllQuery(Class<?> inputClass) {
        return "SELECT * FROM " + getTableName(inputClass);
    }

    private String getTableName(Class<?> inputClass) {
        if (inputClass.isAnnotationPresent(TableName.class)) {
            TableName tableName = inputClass.getAnnotation(TableName.class);
            return tableName.value().toUpperCase();
        }
        return "No Table";
    }

    private String getIdFieldName(Class<?> inputClass) {
        Field[] allFields = inputClass.getDeclaredFields();
        for (Field field : allFields) {
            ColumnName columnName = field.getAnnotation(ColumnName.class);
            if (field.isAnnotationPresent(ColumnName.class) && !columnName.value().equals("") && columnName.value().contains("ID")) {
                return columnName.value().toUpperCase();
            }
        }
        return "No ID";
    }

    private String getValuesForUpdate(Class<?> inputClass) {
        Field[] allFields = inputClass.getDeclaredFields();
        StringBuilder query = new StringBuilder();
        for (Field field : allFields) {
            ColumnName columnName = field.getAnnotation(ColumnName.class);
            if (field.isAnnotationPresent(ColumnName.class) && !columnName.value().equals("") && !columnName.value().contains("ID")) {
                query.append(columnName.value());
                query.append(" = ?, ");
            }
        }
        if (query.length() > 2) {
            query.setLength(query.length() - 2);
        }

        return query.toString().toUpperCase();
    }

    private String getColumnsNameForInsert(Class<?> inputClass) {
        Field[] allFields = inputClass.getDeclaredFields();
        StringBuilder query = new StringBuilder();
        for (Field field : allFields) {
            ColumnName columnName = field.getAnnotation(ColumnName.class);
            if (field.isAnnotationPresent(ColumnName.class) && !columnName.value().equals("") && !columnName.value().contains("ID")) {
                query.append(columnName.value());
                query.append(", ");
                count++;
            }
        }
        if (query.length() > 2) {
            query.setLength(query.length() - 2);
        }
        return query.toString().toUpperCase();
    }

    private String getQuestionMarks() {
        StringBuilder query = new StringBuilder();
        for (int i = 0; i < count; i++) {
            query.append("?, ");
        }
        if (count != 0) {
            query.setLength(query.length() - 2);
        }
        count = 0;
        return query.toString();
    }

}
