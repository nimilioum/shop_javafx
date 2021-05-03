package com.shop.controllers.inventoryStaff;

import com.jfoenix.controls.JFXTextField;
import com.shop.core.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
import java.sql.SQLException;

public class ProductDetailController {
    private Product product;

    public void setProduct(Product product) throws Exception {
        this.product = product;

        image.setImage(new Image(new FileInputStream(product.getImagePath())));
        name.setText(product.getName());
        price.setText("-");
        if (product.getCount() > 0) price.setText(String.valueOf(product.getPrice()));

        description.setText(product.getDescription());
        initSpinner();
        amount.getValueFactory().setValue((int) product.getCount());
    }

    private void initSpinner() {
        amount.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999));
    }

    @FXML
    private AnchorPane content;

    @FXML
    private ImageView image;

    @FXML
    private Text name;

    @FXML
    private Spinner<Integer> amount;

    @FXML
    private JFXTextField price;

    @FXML
    private Text description;

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
    void saveChanges(ActionEvent event) throws Exception {
        product.setPrice(Double.parseDouble(price.getText()));
        product.setCount(amount.getValue());
    }
}
