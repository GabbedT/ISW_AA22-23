package com.example.azienda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageDatore implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private Button button11;

    @FXML
    private Label ProfileName;

    @FXML
    private Button buttonAddEmployee;

    @FXML
    private Button ChangePasswordButton;

    @FXML
    private Button LogoutButton;

    @FXML
    private ImageView ImageNotifications;

    @FXML
    private Button NotificationsButton;

    @FXML
    private Button ProfileButton;

    @FXML
    private Button SearchEmployee;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Database DBMS = new Database();
        User user = DBMS.getUser(DBMS.getLoggedUserID());

        ProfileName.setText(user.getName().toUpperCase() + " " + user.getSurname().toUpperCase());
    }


    public void userLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("Login.fxml");
    }

    public void View(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("Notifications.fxml");
    }

    public void userAddEmployee(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("AddEmployee.fxml");
    }

    @FXML
    void Search(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("SearchEmployee.fxml");
    }

    @FXML
    void Change(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("ModifyPassword.fxml");

    }

    @FXML
    void ViewSchedule(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("CompleteShift.fxml");

    }
}
