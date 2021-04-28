package com.shop.controllers;

import com.shop.App;
import com.shop.core.Item;
import com.shop.core.Order;
import com.shop.core.Product;
import com.shop.core.users.Customer.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CartController {
    public static ArrayList<Item> items = new ArrayList<>();

    public void initialize() throws Exception {
        productBox.getChildren().clear();
        for (Item item : items) {
            addItem(item);
        }
    }

    private void addItem(Item item) throws Exception {
        FXMLLoader loader = App.getFXML("cartItem");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        CartItemController controller = loader.getController();
        controller.setItem(item);
        productBox.getChildren().add(newPane);
        total.setText(String.valueOf(Double.parseDouble(total.getText()) +
                item.getProduct().getPrice() * item.getCount()));
    }

    @FXML
    private AnchorPane content;

    @FXML
    private VBox productBox;

    @FXML
    private Text total;

    @FXML
    void buy(ActionEvent event) throws Exception {
        Order order = new Order((Customer) TemplateController.user);
        order.save();
        order.addItems(items);

        clear();
    }

    @FXML
    void delete(ActionEvent event) {
        clear();
    }

    private void clear() {
        items.clear();
        productBox.getChildren().clear();
        total.setText("0");

        TemplateController.alertSuccess("Purchase completed!");
    }

}
