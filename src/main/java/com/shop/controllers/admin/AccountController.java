package com.shop.controllers.admin;

import com.shop.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AccountController {

    @FXML
    private AnchorPane content;

    @FXML
    void getAdminRegisterPage(ActionEvent event) throws IOException {
        FXMLLoader loader = App.getFXML("admin/adminRegister");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }

    @FXML
    void getSettingsPage(ActionEvent event) throws IOException {
        FXMLLoader loader = App.getFXML("admin/settings");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }

    @FXML
    void getStaffRegisterPage(ActionEvent event) throws IOException {
        FXMLLoader loader = App.getFXML("admin/staffRegister");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }
}
