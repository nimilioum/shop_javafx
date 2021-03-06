package com.shop.controllers.customer;

import com.jfoenix.controls.JFXButton;
import com.shop.App;
import com.shop.controllers.TemplateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AccountController {
    public void initialize() {
        if (TemplateController.user == null) {
            settingsButton.setDisable(true);
            ordersButton.setDisable(true);
        }
    }

    @FXML
    private AnchorPane content;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private JFXButton ordersButton;


    @FXML
    void getOrdersPage(ActionEvent event) throws IOException {
        FXMLLoader loader = App.getFXML("customer/orders");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }

    @FXML
    void getSettingsPage(ActionEvent event) throws Exception {
        FXMLLoader loader = App.getFXML("customer/settings");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }

}
