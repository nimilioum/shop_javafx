package com.shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("template"), 1280, 900);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = getFXML(fxml);
        return fxmlLoader.load();
    }

    public static FXMLLoader getFXML(String fxml) throws IOException {
        return new FXMLLoader(App.class.getResource("ui/" + fxml + ".fxml"));
    }

    public static void main(String[] args) {
        launch();
    }

}