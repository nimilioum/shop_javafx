package com.shop.core.database;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DBConnection {
    private Connection connection;

    private String URL = "localhost:3306/";
    private String USERNAME = "shop";
    private String PASSWORD = "shop";
    private String DBNAME = "shop";

    public DBConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + URL, USERNAME, PASSWORD);
        if (! databaseExists()) {
            initDatabase();
        }
        connection.setCatalog(DBNAME);
    }


    public Connection getConnection() {
        return connection;
    }

    private boolean databaseExists() throws SQLException {
        ResultSet result = connection.getMetaData().getCatalogs();
        while (result.next()) {
            if (result.getString(1).equals(DBNAME)) return true;
        }
        return false;
    }

    private void initDatabase() throws Exception {
        ScriptRunner runner = new ScriptRunner(connection);
        ArrayList<String> files = new ArrayList<>(Arrays.asList("init", "customer", "shopStaff",
                "inventoryStaff", "deliveryStaff", "product_category", "order_item"));

        String file = "init";
        Reader reader = new BufferedReader(new FileReader("sql/" + file + ".sql"));
        runner.runScript(reader);

        Runtime rt = Runtime.getRuntime();
        Process pr;
        String command = "python sql/init.py " + USERNAME + " " + PASSWORD + " " + DBNAME;

        pr = rt.exec(command);
        pr.waitFor();
    }
}
