package com.example.azienda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoPanel implements Initializable {

    @FXML
    private Label infoLabel;

    @FXML
    private Button buttonHomePage;

    @FXML
    void userHomePage(ActionEvent event) {
        HelloApplication n = new HelloApplication();

        Stage stage = (Stage) buttonHomePage.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Database DBMS = new Database();
        infoLabel.setText(DBMS.getInfoMessage());
    }

}
