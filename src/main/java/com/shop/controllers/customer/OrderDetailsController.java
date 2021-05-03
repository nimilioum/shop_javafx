package com.shop.controllers.customer;

import com.shop.App;
import com.shop.core.Item;
import com.shop.core.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class OrderDetailsController {
    private Order order;
    private ArrayList<Item> items;

    public void setOrder(Order order) throws Exception {
        this.order = order;
        items = Item.getOrderItems(order);

        switch (order.getStatus()) {
            case 0 :
                status.setText("Pending");
                break;
            case 1 :
                status.setText("On delivery");
                break;

            case 2 :
                status.setText("Delivered");
                break;
        }

        for (var item : items) addItem(item);
    }

    private void addItem(Item item) throws Exception {
        FXMLLoader loader = App.getFXML("customer/orderItem");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        OrderItemController controller = loader.getController();
        controller.setItem(item);
        itemBox.getChildren().add(newPane);
    }


    @FXML
    private AnchorPane content;

    @FXML
    private Text status;

    @FXML
    private VBox itemBox;

}
