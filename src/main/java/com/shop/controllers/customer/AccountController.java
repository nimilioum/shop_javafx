package com.shop.controllers.customer;

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
