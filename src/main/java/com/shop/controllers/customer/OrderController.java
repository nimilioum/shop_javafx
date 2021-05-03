package com.shop.controllers.customer;

import com.shop.App;
import com.shop.controllers.TemplateController;
import com.shop.core.Order;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;

public class OrderController {

    public void initialize() {
        orderPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    FXMLLoader loader = App.getFXML("customer/orderDetails");
                    loader.load();
                    AnchorPane newPane = loader.getRoot();
                    OrderDetailsController controller = loader.getController();
                    controller.setOrder(order);
                    TemplateController.cPane.getChildren().clear();
                    TemplateController.cPane.getChildren().add(newPane);
                    TemplateController.cPane.toBack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    Order order;

    public void setOrder(Order order) {
        this.order = order;
        code.setText(String.valueOf(order.getId()));
        orderDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(order.getCreationDate()));
        if (order.getStatus() == 2)
            deliverDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(order.getDeliveryDate()));
        price.setText(String.valueOf(order.getTotalPrice()));
    }

    @FXML
    private AnchorPane orderPane;

    @FXML
    private Text code;

    @FXML
    private Text orderDate;

    @FXML
    private Text price;

    @FXML
    private Text deliverDate;

}
