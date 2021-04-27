package com.shop.controllers;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.shop.App;
import com.shop.core.Category;
import com.shop.core.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CategoryListController {
    ArrayList<Category> categories;

    @FXML
    private AnchorPane content;

    @FXML
    private JFXListView<Category> categoryList;

    @FXML
    private JFXTextField searchField;

    @FXML
    void search(ActionEvent event) throws Exception {
        categories.clear();
        categoryList.getItems().clear();
        categories.addAll(Category.searchCategories(searchField.getText()));
        categoryList.getItems().addAll(categories);
    }


    public void initialize() throws Exception {
        categories = Category.getAllCategories();
        categoryList.getItems().addAll(categories);

        categoryList.setCellFactory( tv -> {
            JFXListCell<Category> row = new JFXListCell<>();

            row.setOnMouseClicked(mouseEvent -> {
                try {
                    FXMLLoader loader = App.getFXML("productList");
                    loader.load();
                    AnchorPane newPane = loader.getRoot();
                    ProductListController controller = loader.getController();
                    controller.setProducts(Product.getProductsByCategory(categoryList.getSelectionModel().getSelectedItem()));
                    TemplateController.cPane.getChildren().clear();
                    TemplateController.cPane.getChildren().add(newPane);
                    TemplateController.cPane.toBack();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            return row;
        });
    }
}
