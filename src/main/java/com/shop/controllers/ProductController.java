package com.shop.controllers;

import com.shop.App;
import com.shop.controllers.customer.ProductDetailController;
import com.shop.core.Product;
import com.shop.core.users.Customer.Customer;
import com.shop.core.users.Staff.ShopStaff;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileInputStream;

public class ProductController {
    private Product product;

    public void initialize() {
        productPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    if (TemplateController.user instanceof Customer || TemplateController.user == null) {
                        FXMLLoader loader = App.getFXML("customer/productDetail");
                        loader.load();
                        AnchorPane newPane = loader.getRoot();
                        ProductDetailController controller = loader.getController();
                        controller.setProduct(product);
                        TemplateController.cPane.getChildren().clear();
                        TemplateController.cPane.getChildren().add(newPane);
                        TemplateController.cPane.toBack();
                    }
                    else if (TemplateController.user instanceof ShopStaff) {
                        FXMLLoader loader = App.getFXML("admin/productDetail");
                        loader.load();
                        AnchorPane newPane = loader.getRoot();
                        com.shop.controllers.admin.ProductDetailController controller = loader.getController();
                        controller.setProduct(product);
                        TemplateController.cPane.getChildren().clear();
                        TemplateController.cPane.getChildren().add(newPane);
                        TemplateController.cPane.toBack();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setProduct(Product product) throws Exception {
        this.product = product;
        image.setImage(new Image(new FileInputStream(this.product.getImagePath())));
        name.setText(this.product.getName());
        price.setText(String.valueOf(this.product.getPrice()));
    }

    @FXML
    private ImageView image;

    @FXML
    private Text name;

    @FXML
    private Text price;

    @FXML
    private AnchorPane productPane;

}
