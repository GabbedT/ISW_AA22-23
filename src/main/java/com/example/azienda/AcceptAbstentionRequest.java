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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AcceptAbstentionRequest implements Initializable {

    @FXML
    private Button AcceptButton;

    @FXML
    private Button DenyButton;

    @FXML
    private Label SenderField;

    @FXML
    private Label TitleField;

    @FXML
    private Label DescriptionField;

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

        AbstentionRequest request = DBMS.getAbstentionRequest(DBMS.getSelectedNotificationID());
        User user = DBMS.getUser(request.getSenderID());

        SenderField.setText(user.getSurname() + " " + user.getName());
        TitleField.setText(request.getTitle());

        String fromDateStr = request.getStartAbstention().toString();
        String toDateStr = request.getEndAbstention().toString();

        DescriptionField.setWrapText(true);
        DescriptionField.setText("From: " + fromDateStr + "   To: " + toDateStr + "\n\n" + request.getDescription());
    }

    @FXML
    void AcceptRequest(ActionEvent event) {
        Database DBMS = new Database();

        DBMS.acceptAbstentionRequest(DBMS.getSelectedNotificationID());
        AbstentionRequest request = DBMS.getAbstentionRequest(DBMS.getSelectedNotificationID());
        User user = DBMS.getUser(request.getSenderID());

        String title = "Abstention accepted";
        String description = "Abstention FROM: " + request.getStartAbstention().toString() + " TO: " + request.getEndAbstention();

        Notification employeeNotification = new Notification(title, description, request.getSenderID(), 1);

        if (DBMS.addNotification(employeeNotification)) {
            showInfoPanel("Request accepted! Notification sent to: " + user.getSurname() + " " + user.getName());
            DBMS.modifyGlobalNotificationID(DBMS.getGlobalNotificationID() + 1);
        } else {
            showErrorPanel("Error during database access!");
        }
    }

    @FXML
    void DenyRequest(ActionEvent event) {
        Database DBMS = new Database();
        AbstentionRequest request = DBMS.getAbstentionRequest(DBMS.getSelectedNotificationID());

        if (DBMS.deleteAbstentionRequest(DBMS.getSelectedNotificationID())) {
            User user = DBMS.getUser(request.getSenderID());

            String title = "Abstention denied";
            String description = "Abstention FROM: " + request.getStartAbstention().toString() + " TO: " + request.getEndAbstention();

            Notification employeeNotification = new Notification(title, description, request.getSenderID(), 1);

            if (DBMS.addNotification(employeeNotification)) {
                showErrorPanel("Request denied! Notification sent to: " + user.getSurname() + " " + user.getName());
                DBMS.modifyGlobalNotificationID(DBMS.getGlobalNotificationID() + 1);
            } else {
                showErrorPanel("Error during database access!");
            }
        }
    }

    public void showErrorPanel(String msg) {
        try {
            Database DBMS = new Database();
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
            Database DBMS = new Database();
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
}
