package com.shop.controllers.inventoryStaff;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.shop.controllers.TemplateController;
import com.shop.core.Category;
import com.shop.core.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class AddProductController {
    ObservableList<Category> allCategories = FXCollections.observableArrayList();
    ArrayList<Category> categories = new ArrayList<>();
    String imagePath = "images/default.png";

    public void initialize() throws Exception {
        allCategories.setAll(Category.getAllCategories());
        categoryList.setItems(allCategories);
        image.setImage(new Image(new FileInputStream("images/default.png")));
        categoryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private AnchorPane content;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextField count;

    @FXML
    private JFXTextArea description;

    @FXML
    private ImageView image;

    @FXML
    private JFXListView<Category> categoryList;

    @FXML
    void addCategory(ActionEvent event) {
        categories.addAll(categoryList.getSelectionModel().getSelectedItems());
    }

    @FXML
    void changeImage(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            String path = "images/";
            Files.copy(file.toPath(), new File(path + file.getName()).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

//            product.setImagePath("images/" + file.getName());
            imagePath = "images/" + file.getName();
            image.setImage(new Image(new FileInputStream(imagePath)));
        }
    }

    @FXML
    void save(ActionEvent event) throws Exception {
        Product product = new Product(name.getText(), Double.parseDouble(price.getText()),
                Integer.parseInt(count.getText()), description.getText());
        System.out.println(imagePath);
        product.setImagePath(imagePath);
        product.save();

        for (Category category : categories) product.addCategory(category);

        name.clear();
        price.clear();
        count.clear();
        description.clear();
        categories.clear();
        categoryList.getSelectionModel().clearSelection();

        TemplateController.alertSuccess("Product added successfully!");
    }

}
