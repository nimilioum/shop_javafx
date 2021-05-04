package com.shop.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.shop.App;
import com.shop.core.users.Customer.Customer;
import com.shop.core.users.Staff.DeliveryStaff;
import com.shop.core.users.Staff.InventoryStaff;
import com.shop.core.users.Staff.ShopStaff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private Stage stage;
    ObservableList<String> users = FXCollections.observableArrayList("Customer", "Admin",
            "Inventory Staff", "Delivery staff");

    public void initialize() {
        userType.setItems(users);
    }

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXComboBox<String> userType;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void login(ActionEvent event) throws Exception {
        if (username.getText().equals("") || password.getText().equals(""))
            TemplateController.alertError("Please fill the form!");
        else {
            switch (userType.getValue()) {
                case "Customer" :
                    customerLogin();
                    break;
                case "Admin":
                    adminLogin();
                    break;
                case "Inventory Staff":
                    inventoryLogin();
                    break;
                case "Delivery staff":
                    deliveryLogin();
                    break;
            }
            App.setRoot("template");
            stage.close();
        }
    }

    private void customerLogin() throws Exception {
        Customer user = Customer.find(username.getText(), password.getText());
        if (user == null) TemplateController.alertError("User not found");
        else {
            TemplateController.setUser(user);
        }
    }

    private void adminLogin() throws Exception {
        ShopStaff user = ShopStaff.find(username.getText(), password.getText());
        if (user == null) TemplateController.alertError("User not found");
        else {
            TemplateController.setUser(user);
        }
    }

    private void inventoryLogin() throws Exception {
        InventoryStaff user = InventoryStaff.find(username.getText(), password.getText());
        if (user == null) TemplateController.alertError("User not found");
        else {
            TemplateController.setUser(user);
        }
    }

    private void deliveryLogin() throws Exception {
        DeliveryStaff user = DeliveryStaff.find(username.getText(), password.getText());
        if (user == null) TemplateController.alertError("User not found");
        else {
            TemplateController.setUser(user);

        }
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        TemplateController.getRegisterPage(stage);
    }

}
