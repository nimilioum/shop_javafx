package com.shop.controllers.deliveryStaff;

import com.jfoenix.controls.JFXButton;
import com.shop.App;
import com.shop.controllers.TemplateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AccountController {

    @FXML
    private AnchorPane content;


    @FXML
    void getSettingsPage(ActionEvent event) throws Exception {
        FXMLLoader loader = App.getFXML("deliveryStaff/settings");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }

}
