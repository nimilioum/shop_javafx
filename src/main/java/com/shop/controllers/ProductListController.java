package com.shop.controllers;

import com.jfoenix.controls.JFXTextField;
import com.shop.App;
import com.shop.core.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductListController implements Initializable {
    ObservableList<Product> products = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            products.addAll(Product.getMostSoldProducts());
            boxInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setProducts(ArrayList<Product> products) throws Exception {
        this.products.clear();
        this.products.addAll(products);
        boxInit();

    }

    public void boxInit() throws Exception {
        productBox.getChildren().clear();
        for (Product product : products) {
            addProduct(product);
        }
    }

    private void addProduct(Product product) throws Exception {
        FXMLLoader loader = App.getFXML("product");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        ProductController controller = loader.getController();
        controller.setProduct(product);
        productBox.getChildren().add(newPane);
    }

    @FXML
    private AnchorPane content;

    @FXML
    private JFXTextField searchField;

    @FXML
    private VBox productBox;

    @FXML
    void search(ActionEvent event) throws Exception {
        setProducts(Product.searchProduct(searchField.getText()));
    }
}
