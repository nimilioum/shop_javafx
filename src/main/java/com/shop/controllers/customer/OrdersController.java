package com.shop.controllers.customer;

import com.shop.App;
import com.shop.controllers.TemplateController;
import com.shop.core.Order;
import com.shop.core.users.Customer.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;


public class OrdersController {
    ArrayList<Order> orders;

    public void initialize() throws Exception {
        orders = Order.getCustomerOrders((Customer) TemplateController.user);

        orderBox.getChildren().clear();
        for (var order : orders) addOrder(order);
    }

    private void addOrder(Order order) throws Exception {
        FXMLLoader loader = App.getFXML("customer/order");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        OrderController controller = loader.getController();
        controller.setOrder(order);
        orderBox.getChildren().add(newPane);
    }

    @FXML
    private AnchorPane content;

    @FXML
    private VBox orderBox;

}
