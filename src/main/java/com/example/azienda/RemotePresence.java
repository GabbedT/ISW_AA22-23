package com.example.azienda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class RemotePresence {


        @FXML
        private TextField TextHours;

        @FXML
        private Label SystemTime;

        @FXML
        private TextArea TextReason;


        @FXML
        private Button buttonConfirm4;

        @FXML
        private Button buttonHomePage4;

        @FXML
        private Label WrongInput;


        @FXML
        public void ButtonConfirm(ActionEvent event) throws IOException {
            HelloApplication n = new HelloApplication();
            Database DBMS = new Database();

            User loggedUser = DBMS.getUser(DBMS.getLoggedUserID());
            Shift currentShift = DBMS.getShift(SystemControl.dayOfWeek(), DBMS.getLoggedUserID());

            System.out.println("START HOURS: " + currentShift.getHours(1) + " " + LocalDateTime.now().getHour());
            System.out.println("START MINUTES: " + currentShift.getMinutes(1) + " " + LocalDateTime.now().getMinute());

            System.out.println("END HOURS: " + currentShift.getHours(0) + " " + LocalDateTime.now().getHour());
            System.out.println("END MINUTES: " + currentShift.getMinutes(0) + " " + LocalDateTime.now().getMinute());


            if (currentShift.getHours(1) < LocalDateTime.now().getHour()) { /* Check if the time is after the end of the shift */
                showErrorPanel("Shift has already ended!");
                return;
            } else {
                if (currentShift.getMinutes(1) < LocalDateTime.now().getMinute()) {
                    showErrorPanel("Shift has already ended!");
                    return;
                }
            }

            /* Check if the time is before the start of the shift */
            if (currentShift.getHours(0) > LocalDateTime.now().getHour()) {
                showErrorPanel("Shift has not started!");
                return;
            } else {
                if (currentShift.getMinutes(0) > LocalDateTime.now().getMinute()) {
                    showErrorPanel("Shift has not started!");
                    return;
                }
            }

            /* Send notification */
            String title = "Late entrance";
            String description = "Entrance: " + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + "\n\n" + TextReason.getText();

            Notification notification = new Notification(title, description, 1, loggedUser.getID());

            /* Add shift */
            LocalTime currentTime = LocalTime.now();
            boolean outcome = DBMS.modifyShiftEntrance(currentTime.format(DateTimeFormatter.ofPattern("HH:mm")), SystemControl.dayOfWeek(), SystemControl.weekOfTrimester(true), loggedUser.getID());

            if (DBMS.addNotification(notification) && outcome) {
                DBMS.modifyGlobalNotificationID(DBMS.getGlobalNotificationID() + 1);
                showInfoPanel("Operation completed!");
                n.changeScene("homepageDipendente.fxml");
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

        @FXML
        void UserLogout(ActionEvent event) throws IOException{
            HelloApplication n = new HelloApplication();
            n.changeScene("homepageDipendente.fxml");
        }

        }



