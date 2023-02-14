package com.azienda;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PresenceApplication extends javafx.application.Application {

    private static Stage stg;


    @Override
    public void start(Stage primaryStage) throws IOException {
        /* Open the application GUI */
        FXMLLoader fxmlLoader = new FXMLLoader(AccountApplication.class.getResource("PresenceDetectionModule.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("DetectPresence");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}