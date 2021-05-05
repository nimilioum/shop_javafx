package com.shop.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.shop.App;
import com.shop.core.users.Customer.Customer;
import com.shop.core.users.Person;
import com.shop.core.users.Staff.DeliveryStaff;
import com.shop.core.users.Staff.InventoryStaff;
import com.shop.core.users.Staff.ShopStaff;
import com.shop.core.users.Staff.Staff;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class TemplateController {
    public static AnchorPane cPane;

    public static StackPane stPane;
    private static AnchorPane aPane;
    private static String btnText = "Cart";

    public static Person user = null;

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane pane;

    @FXML
    private MenuButton menuButton;

    @FXML
    private AnchorPane userDetails;

    @FXML
    private JFXButton cartButton;


    @FXML
    private AnchorPane content;

    public static void setUser(Person user) {
        TemplateController.user = user;

        if (user instanceof Staff) btnText = "Orders";
        else btnText = "Cart";
    }

    @FXML
    void getAccountPage(ActionEvent event) throws Exception {
        FXMLLoader loader = null;
        if (user instanceof Customer || user == null)
            loader = App.getFXML("customer/account");
        else if (user instanceof ShopStaff)
            loader = App.getFXML("admin/account");
        else if (user instanceof InventoryStaff)
            loader = App.getFXML("inventoryStaff/account");
        else if (user instanceof DeliveryStaff)
            loader = App.getFXML("deliveryStaff/account");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }

    @FXML
    void getCartPage(ActionEvent event) throws Exception {
        FXMLLoader loader = null;
        if (user instanceof Customer || user == null)
            loader = App.getFXML("customer/cart");
        else if (user instanceof ShopStaff)
            loader = App.getFXML("admin/orders");
        else if (user instanceof InventoryStaff)
            loader = App.getFXML("inventoryStaff/orders");
        else if (user instanceof DeliveryStaff)
            loader = App.getFXML("deliveryStaff/orders");

        loader.load();
        AnchorPane newPane = loader.getRoot();
        content.getChildren().clear();
        content.getChildren().add(newPane);
        content.toBack();
    }

    @FXML
    void getCategoriesPage(ActionEvent event) throws Exception {
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


    public void initialize() throws Exception {

//        user = Customer.find("nima", "saei");
//        user = ShopStaff.find("nima", "saei");
//        user = InventoryStaff.find("nima", "saei");
//        user = DeliveryStaff.find("nima", "saei");

        cartButton.textProperty().bindBidirectional(new SimpleStringProperty(btnText));

        if (user instanceof Staff) cartButton.setText("Orders");

        cPane = content;
        aPane = pane;
        stPane = stackPane;

        getMainPage(new ActionEvent());



        if (user != null) {
            FXMLLoader loader = App.getFXML("authUser");
            loader.load();
            AnchorPane newPane = loader.getRoot();
            userDetails.getChildren().clear();
            userDetails.getChildren().add(newPane);
        }

        else {
            FXMLLoader loader = App.getFXML("notAuthUser");
            loader.load();
            AnchorPane newPane = loader.getRoot();
            userDetails.getChildren().clear();
            userDetails.getChildren().add(newPane);
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

    public static void getRegisterPage() throws IOException {
        FXMLLoader loader = App.getFXML("register");
        Parent root = loader.load();
        Stage stage = new Stage();

        RegisterController controller = loader.getController();
        controller.setStage(stage);

        stage.setScene(new Scene(root, 1280, 750));
        stage.show();
    }

    public static void getRegisterPage(Stage stage) throws IOException {
        FXMLLoader loader = App.getFXML("register");
        Parent root = loader.load();

        RegisterController controller = loader.getController();
        controller.setStage(stage);

        stage.setScene(new Scene(root, 1280, 750));
        stage.show();
    }

    public static void getLoginPage() throws IOException {
        FXMLLoader loader = App.getFXML("login");
        Parent root = loader.load();
        Stage stage = new Stage();
        LoginController controller = loader.getController();
        controller.setStage(stage);

        stage.setScene(new Scene(root, 500, 700));
        stage.show();
    }

    public static void getLoginPage(Stage stage) throws IOException {
        FXMLLoader loader = App.getFXML("login");
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setStage(stage);

        stage.setScene(new Scene(root, 500, 700));
        stage.show();
    }
}
