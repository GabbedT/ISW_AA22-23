package com.azienda.control.shared;

import com.azienda.AccountApplication;
import com.azienda.control.SystemControl;
import com.azienda.database.DatabaseControl;
import com.azienda.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RecoveryPasswordControl {

    @FXML
    private TextField Email;

    @FXML
    private TextField IDNumber;

    @FXML
    private Label WrongInput;
    @FXML
    private Button buttonConfirm1;

    @FXML
    private Button buttonHomePage1;

    public void ButtonConfirm(ActionEvent event) throws IOException {
        checkRetrieve();

    }

    private void checkRetrieve() throws IOException {
        AccountApplication n = new AccountApplication();

        int idInput;
        try {
            idInput = Integer.parseInt(IDNumber.getText());
        } catch (NumberFormatException exc) {
            showErrorPanel("Wrong input format! Insert a number!");

            return;
        }

        if (Email.getText().isEmpty()) {
            showErrorPanel("Insert an email!");
            return;
        }

        String password = SystemControl.generatePassword();
        DatabaseControl db = new DatabaseControl();
        User user = db.getUser(idInput);

        if(Email.getText().equals(user.getEmail())) {
            if (db.modifyPassword(user.getID(), password)) {
                showInfoPanel("Your new Password is: " + password );
                n.changeScene("LoginControl.fxml");
            } else {
                showErrorPanel("Error in database!");
            }
        } else {
            showErrorPanel("Wrong credentials!");
            Email.clear();
            IDNumber.clear();
        }

    }


    public void showErrorPanel(String msg) {
        try {
            DatabaseControl DBMS = new DatabaseControl();
            DBMS.insertErrorMessage(msg);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ErrorPanel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInfoPanel(String msg) {
        try {
            DatabaseControl DBMS = new DatabaseControl();
            DBMS.insertInfoMessage(msg);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InfoPanel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void UserLogout(ActionEvent event) throws IOException {
        AccountApplication m = new AccountApplication();
        m.changeScene("control/shared/LoginModule.fxml");

    }
}
