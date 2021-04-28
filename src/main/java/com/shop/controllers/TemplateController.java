package com.shop.controllers;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.shop.App;
import com.shop.core.users.Customer.Customer;
import com.shop.core.users.Person;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class TemplateController implements Initializable {
    public static AnchorPane cPane;

    public static StackPane stPane;
    private static AnchorPane aPane;

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
    void getCartPage(ActionEvent event) throws Exception {
        FXMLLoader loader = App.getFXML("cart");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
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
        aPane = pane;
        stPane = stackPane;
        try {
            user = Customer.find("nima", "saei");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void alertError(String msg) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stPane, dialogLayout, JFXDialog.DialogTransition.CENTER);

        Label body = new Label(msg);
        body.setStyle("-fx-text-fill: #db3300");
        Label heading = new Label("Error");
        heading.setStyle("-fx-text-fill: #db3300");
        Button btn = new Button();
        btn.setText("OK");
        btn.setStyle("-fx-text-fill: #db3300; -fx-background-color: #ffffff");
        btn.getStyleClass().add("button-flat");
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            dialog.close();
            aPane.setEffect(null);
        });

        dialog.setOnDialogClosed(new EventHandler<JFXDialogEvent>() {


            @Override
            public void handle(JFXDialogEvent jfxDialogEvent) {
                aPane.setEffect(null);
            }
        });

        dialogLayout.setHeading(heading);
        dialogLayout.setBody(body);
        dialogLayout.setActions(btn);
        aPane.setEffect(blur);
        dialog.show();
    }

    public static void alertSuccess(String msg) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stPane, dialogLayout, JFXDialog.DialogTransition.BOTTOM);

        Label body = new Label(msg);
        body.setStyle("-fx-text-fill: #4aeb00; -fx-font-size: 20");
        Label heading = new Label("Success");
        heading.setStyle("-fx-text-fill: #4aeb00");
        Button btn = new Button();
        btn.setText("OK");
        btn.setStyle("-fx-text-fill: #4aeb00; -fx-background-color: #ffffff");
        btn.getStyleClass().add("button-flat");
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            dialog.close();
            aPane.setEffect(null);
        });

        dialog.setOnDialogClosed(new EventHandler<JFXDialogEvent>() {


            @Override
            public void handle(JFXDialogEvent jfxDialogEvent) {
                aPane.setEffect(null);
            }
        });

        dialogLayout.setHeading(heading);
        dialogLayout.setBody(body);
        dialogLayout.setActions(btn);
        aPane.setEffect(blur);
        dialog.show();
    }
}
