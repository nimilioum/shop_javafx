package com.shop.controllers.inventoryStaff;

import com.jfoenix.controls.JFXTextField;
import com.shop.controllers.TemplateController;
import com.shop.core.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AddCategoryController {
    @FXML
    private AnchorPane content;

    @FXML
    private JFXTextField name;

    @FXML
    void add(ActionEvent event) throws Exception {
        if (name.getText().equals("")) TemplateController.alertError("Please provide a name!");
        else if (Category.nameExists(name.getText()))
            TemplateController.alertError("Category already exists!");
        else {
            Category category = new Category(name.getText());
            category.save();

            name.clear();
            TemplateController.alertSuccess("Category added successfully!");
        }
    }
}
