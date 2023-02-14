package com.azienda.generic;

import com.azienda.AccountApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class PresenceDetectionControl {

    @FXML
    private Button EntryButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Label Presence;

    @FXML
    void entry(ActionEvent event) throws IOException {
        AccountApplication n = new AccountApplication();
        n.changeScene("EntranceControl.fxml");
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        AccountApplication n = new AccountApplication();
        n.changeScene("ExitControl.fxml");
    }
}
