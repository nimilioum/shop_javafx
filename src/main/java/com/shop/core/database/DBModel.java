package com.shop.core.database;

import java.sql.Connection;

public interface DBModel {

    public static Connection setConnection() throws Exception {
        return new DBConnection().getConnection();
    }
    public void save() throws Exception;

    public void delete() throws Exception;
}
