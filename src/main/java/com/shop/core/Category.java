package com.shop.core;

import com.shop.core.database.DBModel;

import java.sql.*;
import java.util.ArrayList;

public class Category implements DBModel {
    private Connection connection = null;
    private long id;
    private String name;

    public Category(String name) throws Exception {
        this.name = name;
        connection = DBModel.setConnection();
    }

    public Category(ResultSet query) throws Exception {
        this.id = query.getLong("id");
        this.name = query.getString("name");

        connection = DBModel.setConnection();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void save() throws Exception {
        String query = "call insertCategory(?, ?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setString("inName", name);
        statement.registerOutParameter("id", Types.INTEGER);
        statement.executeUpdate();

        setId(statement.getLong("id"));
        statement.close();
    }

    public static Category find(String name) throws Exception {
        String query = "call findCategory(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_name", name);

        ResultSet resultSet = statement.executeQuery();
        Category category = null;

        if (resultSet.next()) category = new Category(resultSet);

        statement.close();
        return category;
    }

    public static ArrayList<Category> getAllCategories() throws Exception {
        String query = "call getAllCategories()";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);
        ArrayList<Category> categories = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();
        while (!resultSet.isClosed() && resultSet.next()) {
            categories.add(new Category(resultSet));
        }
        statement.close();
        return categories;
    }

    public static ArrayList<Category> searchCategories(String name) throws Exception {
        String query = "call searchCategory(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);
        ArrayList<Category> categories = new ArrayList<>();

        statement.setString("in_name", name);

        ResultSet resultSet = statement.executeQuery();
        while (!resultSet.isClosed() && resultSet.next()) {
            categories.add(new Category(resultSet));
        }
        statement.close();
        return categories;
    }

    @Override
    public void delete() throws Exception {
        String query = "call deleteCategory(?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setLong("categoryId", id);
        statement.executeUpdate();
        statement.close();
    }

    public static boolean nameExists(String name) throws Exception {
        String query = "call categoryNameExists(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("productName", name);
        return statement.executeQuery().next();
    }

    @Override
    public String toString() {
        return name;
    }
}
