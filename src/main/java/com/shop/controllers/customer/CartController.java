package com.shop.controllers.customer;

import com.jfoenix.controls.JFXButton;
import com.shop.App;
import com.shop.controllers.TemplateController;
import com.shop.core.Item;
import com.shop.core.Order;
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
        if (TemplateController.user == null) buyButton.setDisable(true);

        productBox.getChildren().clear();
        for (Item item : items) {
            addItem(item);
        }
    }

    private void addItem(Item item) throws Exception {
        FXMLLoader loader = App.getFXML("customer/cartItem");
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
    private JFXButton buyButton;

    @FXML
    void buy(ActionEvent event) throws Exception {
        Order order = new Order((Customer) TemplateController.user);
        order.setTotalPrice(Double.parseDouble(total.getText()));
        order.save();
        order.addItems(items);

        clear();
        TemplateController.alertSuccess("Purchase completed!");
    }

    @FXML
    void delete(ActionEvent event) {
        clear();
    }

    private void clear() {
        items.clear();
        productBox.getChildren().clear();
        total.setText("0");
    }

}
