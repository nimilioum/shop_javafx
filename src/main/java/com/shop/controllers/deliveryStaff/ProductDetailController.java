package com.shop.controllers.deliveryStaff;

import com.jfoenix.controls.JFXButton;
import com.shop.controllers.customer.CartController;
import com.shop.core.Item;
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

public class ProductDetailController {
    Product product;

    public void setProduct(Product product) throws Exception {
        this.product = product;
        image.setImage(new Image(new FileInputStream(product.getImagePath())));
        name.setText(product.getName());
        price.setText(String.valueOf(product.getPrice()));

        description.setText(product.getDescription());

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
    private JFXButton buy;

}
