package com.shop.controllers.deliveryStaff;

import com.jfoenix.controls.JFXComboBox;
import com.shop.App;
import com.shop.controllers.TemplateController;
import com.shop.core.Item;
import com.shop.core.Order;
import com.shop.core.users.Staff.DeliveryStaff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        FXMLLoader loader = App.getFXML("deliveryStaff/orderItem");
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

    @FXML
    void delivered(ActionEvent event) throws Exception {
        order.setStatus(2);
        order.setDeliveryDate(new Date());

        TemplateController.alertSuccess("Delivered!");
    }
}
