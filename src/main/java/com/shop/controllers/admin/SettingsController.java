package com.shop.controllers.admin;

import com.jfoenix.controls.JFXTextField;
import com.shop.controllers.TemplateController;
import com.shop.core.users.Customer.Customer;
import com.shop.core.users.Staff.ShopStaff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SettingsController {
    private ShopStaff admin;

    public void initialize() {
        admin = (ShopStaff) TemplateController.user;
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
    void done(ActionEvent event) throws Exception {
        boolean alert = false;
        if (!newUsername.getText().equals("")) {
            admin.updateUsername(newUsername.getText());
            alert = true;
        }
        if (!newEmail.getText().equals("")) {
            admin.updateEmail(newEmail.getText());
            alert = true;
        }
        if (!newPhone.getText().equals("")) {
            admin.updatePhone(newPhone.getText());
            alert = true;
        }
        if (!newPass.getText().equals("")) {
            if (currentPass.getText().equals(admin.getPassword()) && newPass.getText().equals(confirmPass.getText())) {
                admin.updatePassword(newPass.getText());
                alert = true;
            }
            TemplateController.alertError("Passwords do not match or incorrect password");
        }
        if (alert) TemplateController.alertSuccess("Changes saved!");
    }
}
