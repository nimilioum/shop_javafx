package com.shop.controllers;

import com.shop.App;
import com.shop.core.users.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class TemplateController implements Initializable {
    public static AnchorPane cPane;

    public static Person user = null;

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane pane;

    @FXML
    private MenuButton menuButtton;

    @FXML
    private AnchorPane userDetails;

    @FXML
    private AnchorPane content;

    @FXML
    void getAccountPage(ActionEvent event) {

    }

    @FXML
    void getCartPage(ActionEvent event) {

    }

    @FXML
    void getCategoriesPage(ActionEvent event) throws IOException {
        FXMLLoader loader = App.getFXML("categoriesList");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }

    @FXML
    void getMainPage(ActionEvent event) throws IOException {
        FXMLLoader loader = App.getFXML("productList");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cPane = content;
    }
}
