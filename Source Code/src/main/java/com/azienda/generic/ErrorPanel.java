package com.azienda.generic;

import com.azienda.AccountApplication;
import com.azienda.database.DatabaseControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorPanel implements Initializable {

    @FXML
    private Button buttonHomePage;
    @FXML
    private Label ErrorLabel;

    @FXML
    void userHomePage(ActionEvent event) {
        AccountApplication n = new AccountApplication();

        Stage stage = (Stage) buttonHomePage.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseControl DBMS = new DatabaseControl();
        ErrorLabel.setText(DBMS.getErrorMessage());
    }
}
