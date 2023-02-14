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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyPasswordControl {

    @FXML
    private Button buttonConfirmChangePassword;

    @FXML
    private Button buttonHomePage;
    @FXML
    private PasswordField ConfirmPassword;

    @FXML
    private PasswordField NewPassword;

    @FXML
    private PasswordField OldPassword;

    @FXML
    private TextField Email;

    @FXML
    private Label WrongInput;

    @FXML
    void ButtonConfirm(ActionEvent event) throws IOException {
        AccountApplication n = new AccountApplication();
        DatabaseControl DBMS = new DatabaseControl();

        if (isEmpty(Email)) {
            showErrorPanel("Insert the email!");


            return;
        } else if (isEmpty(NewPassword)) {
            showErrorPanel("Insert the new password!");


            return;
        } else if (isEmpty(ConfirmPassword)) {
            showErrorPanel("Insert the new password confirmation!");

            return;
        } else if (isEmpty(OldPassword)) {
            showErrorPanel("Insert the old password!");

            return;
        }

        User loggedUser = DBMS.getUser(DBMS.getLoggedUserID());
        if (!Email.getText().equals(loggedUser.getEmail())) {
            showErrorPanel("Wrong email inserted!");
            Email.clear();

            return;
        } else if (!OldPassword.getText().equals(loggedUser.getPassword())) {
            showErrorPanel("Wrong password inserted");
            OldPassword.clear();

            return;
        } else if (!NewPassword.getText().equals(ConfirmPassword.getText())) {
            showErrorPanel("Confirm password field must match the new password field!");
            NewPassword.clear();

            return;
        }

        if (DBMS.modifyPassword(loggedUser.getID(), NewPassword.getText())) {
            showInfoPanel("Operation completed");

            if (loggedUser.getID() == 1) {
                n.changeScene("control/employer/HomepageEmployerModule.fxml");
            } else {
                n.changeScene("control/employee/HomepageEmployeeModule.fxml");
            }
        } else {
            showErrorPanel("DatabaseControl error!");
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

    @FXML
    void userHomePage(ActionEvent event) throws IOException {
        AccountApplication n = new AccountApplication();
        DatabaseControl DBMS = new DatabaseControl();


        if (DBMS.getLoggedUserID() == 1) {
            n.changeScene("control/employer/HomepageEmployerModule.fxml");
        } else {
            n.changeScene("control/employee/HomepageEmployeeModule.fxml");
        }
    }

    private boolean isEmpty(TextField label) {
        return label.getText().isEmpty();
    }

}
