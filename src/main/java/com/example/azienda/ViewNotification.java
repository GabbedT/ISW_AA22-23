package com.example.azienda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewNotification implements Initializable {

    @FXML
    private Label DescriptionField;

    @FXML
    private Label SenderField;

    @FXML
    private Label TitleField;

    @FXML
    private Button goBackButton;

    @FXML
    void GoHome(ActionEvent event) throws IOException {
        HelloApplication n = new HelloApplication();

        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Database DBMS = new Database();

        Notification notification = DBMS.getNotification(DBMS.getSelectedNotificationID());
        User user = DBMS.getUser(notification.getSenderID());

        SenderField.setText(user.getSurname() + " " + user.getName());
        TitleField.setText(notification.getTitle());

        DescriptionField.setWrapText(true);
        DescriptionField.setText(notification.getDescription());

        if (!DBMS.deleteNotification(DBMS.getSelectedNotificationID())) {
            System.err.println("ERROR ON DELETING THE NOTIFICATION");
        }
    }
}