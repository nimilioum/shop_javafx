package com.shop.core;

import com.shop.core.database.DBModel;

import java.sql.*;

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

    @Override
    public void delete() throws Exception {
        String query = "call deleteCategory(?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setLong("categoryId", id);
        statement.executeUpdate();
        statement.close();
    }
}
