package com.shop.controllers.inventoryStaff;

import com.shop.core.Item;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileInputStream;

public class OrderItemController {
    Item item;

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
}
