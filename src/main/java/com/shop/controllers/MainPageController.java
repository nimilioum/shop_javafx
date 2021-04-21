package com.shop.controllers;

import com.jfoenix.controls.JFXTextField;
import com.shop.App;
import com.shop.core.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    ArrayList<Product> products;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            products = Product.getAllProducts();
            boxInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void boxInit() throws Exception {
        for (Product product : products) {
            FXMLLoader loader = App.getFXML("product");

            loader.load();
            AnchorPane newPane = loader.getRoot();
            ProductController controller = loader.getController();
            controller.setProduct(product);
            productBox.getChildren().add(newPane);
        }
    }

    @FXML
    private AnchorPane content;

    @FXML
    private JFXTextField searchField;

    @FXML
    private VBox productBox;

    @FXML
    void search(ActionEvent event) {

    }
}
