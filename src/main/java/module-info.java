module com.shop {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires com.jfoenix;

    opens com.shop to javafx.fxml;
    opens com.shop.controllers to javafx.base, javafx.fxml;
    opens com.shop.core to javafx.base;

    exports com.shop;
}