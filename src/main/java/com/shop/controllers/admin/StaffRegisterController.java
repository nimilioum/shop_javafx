package com.shop.controllers.admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.shop.controllers.TemplateController;
import com.shop.core.users.Staff.DeliveryStaff;
import com.shop.core.users.Staff.InventoryStaff;
import com.shop.core.users.Staff.ShopStaff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class StaffRegisterController {
    ObservableList<String> userTypes = FXCollections.observableArrayList("Delivery", "Inventory");

    public void initialize() {
        staffType.setItems(userTypes);
    }

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
    private JFXComboBox<String> staffType;

    @FXML
    void register(ActionEvent event) throws Exception {
        if (fname.getText().equals("") || lname.getText().equals("") || email.getText().equals("") ||
                phone.getText().equals("") || nc.getText().equals("") || username.getText().equals("") ||
                password.getText().equals("") || confirmPassword.getText().equals("") || staffType.getValue() == null)
            TemplateController.alertError("Please fill the form!");




        else {
            if (staffType.getValue().equals("Inventory")) registerInventory();
            else if (! password.getText().equals(confirmPassword.getText()))
                TemplateController.alertError("Passwords do not match!");
            else registerDelivery();

            TemplateController.alertSuccess("User Created Successfully!");

            fname.clear();
            lname.clear();
            email.clear();
            username.clear();
            password.clear();
            confirmPassword.clear();
            nc.clear();
            phone.clear();
        }
    }

    private void registerInventory() throws Exception {
        if (InventoryStaff.usernameExists(username.getText()))
            TemplateController.alertError("Username Already exists!");

        else if (InventoryStaff.emailExists(email.getText()))
            TemplateController.alertError("Email Already exists!");

        else if (InventoryStaff.phoneNumberExists(phone.getText()))
            TemplateController.alertError("Phone number Already exists!");

        else {
            InventoryStaff staff = new InventoryStaff(fname.getText(), lname.getText(), email.getText(),
                    username.getText(), password.getText(), nc.getText(), phone.getText());

            staff.save();
        }
    }

    private void registerDelivery() throws Exception {
        if (DeliveryStaff.usernameExists(username.getText()))
            TemplateController.alertError("Username Already exists!");

        else if (DeliveryStaff.emailExists(email.getText()))
            TemplateController.alertError("Email Already exists!");

        else if (DeliveryStaff.phoneNumberExists(phone.getText()))
            TemplateController.alertError("Phone number Already exists!");

        else {
            DeliveryStaff staff = new DeliveryStaff(fname.getText(), lname.getText(), email.getText(),
                    username.getText(), password.getText(), nc.getText(), phone.getText());

            staff.save();
        }
    }
}
