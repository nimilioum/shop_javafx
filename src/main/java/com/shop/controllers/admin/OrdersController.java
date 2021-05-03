package com.shop.controllers.admin;

import com.jfoenix.controls.JFXTextField;
import com.shop.App;
import com.shop.controllers.TemplateController;
import com.shop.core.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Date;

public class OrdersController {
    ObservableList<Order> orders = FXCollections.observableArrayList();
    public void initialize() throws Exception {
        orders.setAll(Order.getAllOrders());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        date.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        orderTable.setItems(orders);

        orderTable.setRowFactory(tv -> {
            TableRow<Order> row = new TableRow<>();

            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && !row.isEmpty()) {
                    try {
                        FXMLLoader loader = App.getFXML("admin/orderDetail");
                        loader.load();
                        AnchorPane newPane = loader.getRoot();
                        OrderDetailController controller = loader.getController();
                        controller.setOrder(orderTable.getSelectionModel().getSelectedItem());
                        TemplateController.cPane.getChildren().clear();
                        TemplateController.cPane.getChildren().add(newPane);
                        TemplateController.cPane.toBack();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            return row;
        });
    }

    @FXML
    private AnchorPane content;

    @FXML
    private JFXTextField search;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, Long> id;

    @FXML
    private TableColumn<Order, String> name;

    @FXML
    private TableColumn<Order, Date> date;

    @FXML
    private TableColumn<Order, Integer> status;

    @FXML
    void search(ActionEvent event) {

    }
}
