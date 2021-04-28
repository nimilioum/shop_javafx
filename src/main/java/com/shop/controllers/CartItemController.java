package com.shop.controllers;

import com.shop.App;
import com.shop.core.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;

public class CartItemController {
    private Item item;

    public void initialize() {

        productPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    FXMLLoader loader = App.getFXML("customerProductDetail");
                    loader.load();
                    AnchorPane newPane = loader.getRoot();
                    CustomerProductDetailController controller = loader.getController();
                    controller.setProduct(item.getProduct());
                    TemplateController.cPane.getChildren().clear();
                    TemplateController.cPane.getChildren().add(newPane);
                    TemplateController.cPane.toBack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setItem(Item item) throws Exception {
        this.item = item;

        image.setImage(new Image(new FileInputStream(this.item.getProduct().getImagePath())));
        name.setText(this.item.getProduct().getName());
        price.setText(String.valueOf(this.item.getProduct().getPrice()));
        count.setText(String.valueOf(this.item.getCount()));
    }

    @FXML
    private AnchorPane productPane;

    @FXML
    private ImageView image;

    @FXML
    private Text name;

    @FXML
    private Text price;

    @FXML
    private Text count;

    @FXML
    void delete(ActionEvent event) throws Exception {
        CartController.items.remove(this.item);

        FXMLLoader loader = App.getFXML("cart");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        CartController controller = loader.getController();
//        controller.setProduct(item.getProduct());
        TemplateController.cPane.getChildren().clear();
        TemplateController.cPane.getChildren().add(newPane);
        TemplateController.cPane.toBack();
    }
}
