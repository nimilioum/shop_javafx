module com.shop {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires com.jfoenix;

    opens com.shop to javafx.fxml;
    opens com.shop.controllers to javafx.base, javafx.fxml;
    opens com.shop.controllers.admin to javafx.base, javafx.fxml;
    opens com.shop.controllers.inventoryStaff to javafx.base, javafx.fxml;
    opens com.shop.controllers.deliveryStaff to javafx.base, javafx.fxml;
    opens com.shop.core to javafx.base;

    exports com.shop;
    opens com.shop.controllers.customer to javafx.base, javafx.fxml;
}