package com.azienda.control.shared;

import com.azienda.AccountApplication;
import com.azienda.database.DatabaseControl;
import com.azienda.entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

import javax.swing.JOptionPane;
import javafx.scene.control.Hyperlink;

import java.io.IOException;


public class LoginControl {

    public LoginControl() {

    }

    @FXML
    private Button button;
    @FXML
    private Label WrongLogin;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;
    @FXML
    private Hyperlink RetrivePassword;


    public void UserLogin(ActionEvent event) throws IOException {
        checkLogin();

    }

    private void checkLogin() throws IOException {

        AccountApplication n = new AccountApplication();
        DatabaseControl DBMS = new DatabaseControl();

        if (Email.getText().isEmpty() && Password.getText().isEmpty()) {
            showErrorPanel("Please enter your data.");
        }

             User user = DBMS.getUser(Email.getText(), Password.getText());
             System.out.println(user);

        if (user == null) {
            Email.clear();
            Password.clear();
            showErrorPanel("Wrong username or password!");
        } else {
            DBMS.modifyLoggedUserID(user.getID());

            if (user.getID()==1){
                n.changeScene("control/employer/HomepageEmployerModule.fxml");
            }else {
                n.changeScene("control/employee/HomepageEmployeeModule.fxml");
            }

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


    public void UserRetrive(ActionEvent event) throws IOException {

        Retrive();

    }

    private void Retrive() throws IOException {
        AccountApplication n = new AccountApplication();

        n.changeScene("control/shared/RecoveryPasswordModule.fxml");
    }
}
