package com.shop.controllers.customer;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.shop.controllers.TemplateController;
import com.shop.core.users.Customer.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SettingsController {
    private Customer customer;
    public void initialize() {
        customer = (Customer) TemplateController.user;
    }


    @FXML
    private AnchorPane content;

    @FXML
    private JFXTextField newUsername;

    @FXML
    private JFXTextField currentPass;

    @FXML
    private JFXTextField newPass;

    @FXML
    private JFXTextField confirmPass;

    @FXML
    private JFXTextField newEmail;

    @FXML
    private JFXTextField newPhone;

    @FXML
    private JFXTextArea newAddress;

    @FXML
    void done(ActionEvent event) throws Exception {
        if (! newUsername.getText().equals("")) customer.updateUsername(newUsername.getText());
        if (! newEmail.getText().equals("")) customer.updateEmail(newEmail.getText());
        if (! newPhone.getText().equals("")) customer.updatePhone(newPhone.getText());
        if (! newAddress.getText().equals("")) customer.updateAddress(newAddress.getText());
        if (! newPass.getText().equals("")) {
            if (newPass.getText().equals(customer.getPassword()) && newPass.getText().equals(confirmPass.getText()))
                customer.updatePassword(newPass.getText());
        }
    }
}
