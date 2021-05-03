package com.shop.controllers.inventoryStaff;

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
    void getAddCategoryPage(ActionEvent event) throws Exception {
        FXMLLoader loader = App.getFXML("inventoryStaff/addCategory");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }

    @FXML
    void getAddProductPage(ActionEvent event) throws Exception {
        FXMLLoader loader = App.getFXML("inventoryStaff/addProduct");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }

    @FXML
    void getSettingsPage(ActionEvent event) throws IOException {
        FXMLLoader loader = App.getFXML("inventoryStaff/settings");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }

}
