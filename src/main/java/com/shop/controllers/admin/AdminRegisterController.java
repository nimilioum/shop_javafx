package com.shop.controllers.admin;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.shop.controllers.TemplateController;
import com.shop.core.users.Staff.ShopStaff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AdminRegisterController {

    @FXML
    private AnchorPane content;

    @FXML
    private JFXTextField fname;

    @FXML
    private JFXTextField lname;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField nc;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmPassword;

    @FXML
    private JFXCheckBox isSuperAdmin;

    @FXML
    void register(ActionEvent event) throws Exception {
        boolean superAdmin = isSuperAdmin.isSelected();

        if (fname.getText().equals("") || lname.getText().equals("") || email.getText().equals("") ||
            phone.getText().equals("") || nc.getText().equals("") || username.getText().equals("") ||
            password.getText().equals("") || confirmPassword.getText().equals(""))
            TemplateController.alertError("Please fill the form!");

        else if (ShopStaff.usernameExists(username.getText()))
            TemplateController.alertError("Username Already exists!");

        else if (ShopStaff.emailExists(email.getText()))
            TemplateController.alertError("Email Already exists!");

        else if (ShopStaff.phoneNumberExists(phone.getText()))
            TemplateController.alertError("Phone number Already exists!");

        else if (! password.getText().equals(confirmPassword.getText()))
            TemplateController.alertError("Passwords do not match!");

        else {
            ShopStaff admin = new ShopStaff(fname.getText(), lname.getText(), email.getText(),
                    username.getText(), password.getText(), nc.getText(), phone.getText());
            admin.setSuperAdmin(superAdmin);

            admin.save();

            TemplateController.alertSuccess("User Created Successfully!");

            fname.clear();
            lname.clear();
            email.clear();
            username.clear();
            password.clear();
            confirmPassword.clear();
            nc.clear();
            phone.clear();
            isSuperAdmin.setSelected(false);
        }
    }
}
