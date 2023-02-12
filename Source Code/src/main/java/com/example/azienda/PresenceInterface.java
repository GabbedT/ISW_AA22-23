package com.example.azienda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class PresenceInterface {

    @FXML
    private Button EntryButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Label Presence;

    @FXML
    void entry(ActionEvent event) throws IOException {
        HelloApplication n = new HelloApplication();
        n.changeScene("Entrance.fxml");
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        HelloApplication n = new HelloApplication();
        n.changeScene("Exit.fxml");
    }
}
