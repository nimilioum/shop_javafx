package com.shop.controllers.admin;

import com.shop.App;
import com.shop.controllers.ProductController;
import com.shop.core.Item;
import com.shop.core.Order;
import com.shop.core.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderDetailController {
    private Order order;
    ArrayList<Item> items = new ArrayList<>();

    public void setOrder(Order order) throws Exception {
        this.order = order;

        items = order.getItems();
        boxInit();
    }

    private void boxInit() throws Exception {
        itemBox.getChildren().clear();
        id.setText(String.valueOf(order.getId()));
        price.setText(String.valueOf(order.getTotalPrice()));
        name.setText(order.getCustomerName());
        orderDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(order.getCreationDate()));
        for (Item item : items) {
            addItem(item);
        }

        switch (order.getStatus()) {
            case 0 :
                status.setText("Pending");
                break;
            case 1 :
                status.setText("On delivery");
                break;
            case 2 :
                status.setText("Delivered");
                deliveryDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(order.getDeliveryDate()));
        }
    }

    private void addItem(Item item) throws Exception {
        FXMLLoader loader = App.getFXML("admin/orderItem");
        loader.load();
        AnchorPane newPane = loader.getRoot();
        OrderItemController controller = loader.getController();
        controller.setItem(item);
        itemBox.getChildren().add(newPane);
    }

    @FXML
    private AnchorPane content;

    @FXML
    private VBox itemBox;

    @FXML
    private Text id;

    @FXML
    private Text name;

    @FXML
    private Text price;

    @FXML
    private Text status;

    @FXML
    private Text orderDate;

    @FXML
    private Text deliveryDate;
}
