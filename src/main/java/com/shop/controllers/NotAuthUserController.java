package com.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class NotAuthUserController {

    @FXML
    void login(ActionEvent event) throws IOException {
        TemplateController.getLoginPage();
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        TemplateController.getRegisterPage();
    }
}
