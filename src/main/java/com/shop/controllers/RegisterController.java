package com.shop.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.shop.App;
import com.shop.core.users.Customer.Customer;
import com.shop.core.users.Staff.ShopStaff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    private Stage stage;

    @FXML
    private AnchorPane content;

    @FXML
    private JFXTextField fname;

    @FXML
    private JFXTextField lname;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmPassword;

    @FXML
    private JFXCheckBox isSuperAdmin;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void register(ActionEvent event) throws Exception {

        if (fname.getText().equals("") || lname.getText().equals("") || email.getText().equals("") ||
             username.getText().equals("") || password.getText().equals("") || confirmPassword.getText().equals(""))
            TemplateController.alertError("Please fill the form!");

        else if (Customer.usernameExists(username.getText()))
            TemplateController.alertError("Username Already exists!");

        else if (Customer.emailExists(email.getText()))
            TemplateController.alertError("Email Already exists!");

        else if (! password.getText().equals(confirmPassword.getText()))
            TemplateController.alertError("Passwords do not match!");

        else {
            Customer customer = new Customer(fname.getText(), lname.getText(), email.getText(),
                    username.getText(), password.getText());

            customer.save();
            TemplateController.setUser(customer);
            TemplateController.alertSuccess("User Created Successfully!");

            fname.clear();
            lname.clear();
            email.clear();
            username.clear();
            password.clear();
            confirmPassword.clear();

            App.setRoot("template");
            stage.close();

        }
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        TemplateController.getLoginPage(stage);
    }
}
