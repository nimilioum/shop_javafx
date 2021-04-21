package com.shop.controllers;

import com.shop.App;
import com.shop.core.Product;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProductController {
    private Product product;

    public void initialize() {
        productPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

//                System.out.println(product.getName());

                try {
                    FXMLLoader loader = App.getFXML("productDetail");
                    loader.load();
                    AnchorPane newPane = loader.getRoot();
                    ProductDetailController controller = loader.getController();
                    controller.setProduct(product);
                    TemplateController.cPane.getChildren().clear();
                    TemplateController.cPane.getChildren().add(newPane);
                    TemplateController.cPane.toBack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setProduct(Product product) throws Exception {
        this.product = product;
        image.setImage(new Image(new FileInputStream(this.product.getImagePath())));
        name.setText(this.product.getName());
        price.setText(String.valueOf(this.product.getPrice()));
    }

    @FXML
    private ImageView image;

    @FXML
    private Text name;

    @FXML
    private Text price;

    @FXML
    private AnchorPane productPane;

}
