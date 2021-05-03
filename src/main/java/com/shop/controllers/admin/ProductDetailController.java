package com.shop.controllers.admin;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.shop.core.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ProductDetailController {
    private Product product;

    public void setProduct(Product product) throws Exception {
        this.product = product;

        image.setImage(new Image(new FileInputStream(product.getImagePath())));
        name.setText(product.getName());
        price.setText("-");
        if (product.getCount() > 0) price.setText(String.valueOf(product.getPrice()));

        description.setText(product.getDescription());
    }

    @FXML
    private AnchorPane content;

    @FXML
    private ImageView image;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField price;

    @FXML
    private Text sales;

    @FXML
    private JFXTextArea description;

    @FXML
    void changePhoto(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            String path = "images/";
            Files.copy(file.toPath(), new File(path + file.getName()).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            product.setImagePath("images/" + file.getName());
            image.setImage(new Image(new FileInputStream(product.getImagePath())));
        }
    }

    @FXML
    void save(ActionEvent event) throws Exception {
        product.setPrice(Double.parseDouble(price.getText()));
        product.setName(name.getText());
        product.setDescription(description.getText());
    }


}
