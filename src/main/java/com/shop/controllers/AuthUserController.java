package com.shop.controllers;

import com.shop.App;
import com.shop.core.users.Customer.Customer;
import com.shop.core.users.Person;
import com.shop.core.users.Staff.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class AuthUserController {

    public void initialize() {
        Person person = TemplateController.user;
        username.setText(person.getUsername());
        name.setText(person.getFname() + " " + person.getLname());

        if (person instanceof Customer) role.setText("Customer");
        else if (person instanceof Staff) role.setText("Admin");
    }

    @FXML
    private Text username;

    @FXML
    private Text role;

    @FXML
    private Text name;

    @FXML
    void logout(ActionEvent event) throws IOException {
        TemplateController.user = null;

        App.setRoot("template");
    }
}
