package com.shop.controllers.inventoryStaff;

import com.jfoenix.controls.JFXTextField;
import com.shop.controllers.TemplateController;
import com.shop.core.users.Staff.InventoryStaff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SettingsController {
    private InventoryStaff staff;

    public void initialize() {
        staff = (InventoryStaff) TemplateController.user;
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
            staff.updateUsername(newUsername.getText());
            alert = true;
        }
        if (!newEmail.getText().equals("")) {
            staff.updateEmail(newEmail.getText());
            alert = true;
        }
        if (!newPhone.getText().equals("")) {
            staff.updatePhone(newPhone.getText());
            alert = true;
        }
        if (!newPass.getText().equals("")) {
            if (currentPass.getText().equals(staff.getPassword()) && newPass.getText().equals(confirmPass.getText())) {
                staff.updatePassword(newPass.getText());
                alert = true;
            }
            TemplateController.alertError("Passwords do not match or incorrect password");
        }
        if (alert) TemplateController.alertSuccess("Changes saved!");
    }
}
