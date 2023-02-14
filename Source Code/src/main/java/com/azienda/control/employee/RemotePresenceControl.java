package com.azienda.control.employee;

import com.azienda.AccountApplication;
import com.azienda.control.SystemControl;
import com.azienda.database.DatabaseControl;
import com.azienda.entity.Notification;
import com.azienda.entity.Shift;
import com.azienda.entity.User;
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

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class RemotePresenceControl {


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
            AccountApplication n = new AccountApplication();
            DatabaseControl DBMS = new DatabaseControl();

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

            if (TextReason.getText().isEmpty()) {
                showErrorPanel("Insert a valid reason!");
                return;
            }

            /* Send notification */
            String title = "Late entrance";
            String description = "EntranceControl: " + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + "\n\n" + TextReason.getText();

            Notification notification = new Notification(title, description, 1, loggedUser.getID());

            /* Add shift */
            LocalTime currentTime = LocalTime.now();
            boolean outcome = DBMS.modifyShiftEntrance(currentTime.format(DateTimeFormatter.ofPattern("HH:mm")), SystemControl.dayOfWeek(), SystemControl.weekOfTrimester(true), loggedUser.getID());

            if (DBMS.addNotification(notification) && outcome) {
                DBMS.modifyGlobalNotificationID(DBMS.getGlobalNotificationID() + 1);
                showInfoPanel("Operation completed!");
                n.changeScene("control/employee/HomepageEmployeeModule.fxml");
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
        void UserLogout(ActionEvent event) throws IOException{
            AccountApplication n = new AccountApplication();
            n.changeScene("control/employee/HomepageEmployeeModule.fxml");
        }

        }



