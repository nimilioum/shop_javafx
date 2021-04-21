package com.shop.core.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private Connection connection;

    private String URL = "localhost:3306/";
    private String USERNAME = "shop";
    private String PASSWORD = "shop";
    private String DBNAME = "shop";

    public DBConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + URL, USERNAME, PASSWORD);
        connection.setCatalog(DBNAME);
    }


    public Connection getConnection() {
        return connection;
    }
}
