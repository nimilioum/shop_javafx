package com.shop.controllers;

import com.jfoenix.controls.JFXButton;
import com.shop.core.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProductDetailController {
    Product product;

    public void setProduct(Product product) throws Exception {
        this.product = product;
        image.setImage(new Image(new FileInputStream(product.getImagePath())));
        name.setText(product.getName());
        price.setText("-");
        if (product.getCount() > 0) price.setText(String.valueOf(product.getPrice()));
        else buy.setDisable(true);

        description.setText(product.getDescription());
        initSpinner();

    }

    private void initSpinner() {
        amount.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, (int) product.getCount()));
    }

    @FXML
    private AnchorPane content;

    @FXML
    private ImageView image;

    @FXML
    private Text name;

    @FXML
    private Text price;

    @FXML
    private Text description;

    @FXML
    private Spinner<Integer> amount;

    @FXML
    private JFXButton buy;

    @FXML
    void addToCart(ActionEvent event) {

    }

}
