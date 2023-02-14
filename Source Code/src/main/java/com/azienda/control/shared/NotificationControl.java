package com.azienda.control.shared;

import com.azienda.AccountApplication;
import com.azienda.database.DatabaseControl;
import com.azienda.entity.Notification;
import com.azienda.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotificationControl implements Initializable {

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
        AccountApplication n = new AccountApplication();

        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseControl DBMS = new DatabaseControl();

        Notification notification = DBMS.getNotification(DBMS.getSelectedNotificationID());
        User user = DBMS.getUser(notification.getSenderID());

        SenderField.setText(user.getSurname() + " " + user.getName());
        TitleField.setText(notification.getTitle());

        DescriptionField.setText(notification.getDescription());
        DescriptionField.setWrapText(true);

        if (!DBMS.deleteNotification(DBMS.getSelectedNotificationID())) {
            System.err.println("ERROR ON DELETING THE NOTIFICATION");
        }
    }
}