package com.azienda.generic.presence;

import com.azienda.control.SystemControl;
import com.azienda.database.DatabaseControl;
import com.azienda.entity.Shift;
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

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EntranceControl {

    @FXML
    private Label entrance;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;


    void confirmPresence(ActionEvent event) {
        DatabaseControl DBMS = new DatabaseControl();

        User usr = DBMS.getUser(Integer.parseInt(id.getText()));

        if (usr == null) {
            showErrorPanel("Wrong ID inserted!");
            return;
        }

        if (!usr.getName().equals(name.getText())) {
            showErrorPanel("Wrong name inserted!");
            return;
        }

        if (!usr.getSurname().equals(surname.getText())) {
            showErrorPanel("Wrong surname inserted!");
            return;
        }

        String entrance = LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();
        Shift currentShift = DBMS.getShift(LocalDateTime.now().getDayOfWeek().getValue(), SystemControl.weekOfTrimester(true), usr.getID());

        /* Check if not in time (entrance > expectedEntrance) AND (entrance < expectedExit) */
        if (!SystemControl.checkStartTime(currentShift.getExpectedEntrance(), entrance) & SystemControl.checkStartTime(currentShift.getExpectedExit(), entrance)) {
            showErrorPanel("Limit exceeded, proceed with remote presence detection!");
        } else if (!SystemControl.checkStartTime(currentShift.getExpectedExit(), entrance)) {
            /* Check if taking presence after shift (entrance > expectedExit) */
            showErrorPanel("Your shift has already ended!");
        } else {
            if (!DBMS.modifyShiftEntrance(entrance, LocalDateTime.now().getDayOfWeek().getValue(), SystemControl.weekOfTrimester(true), usr.getID())) {
                showErrorPanel("Error while accessing into database");
                return;
            }
        }

        showInfoPanel("Operation completed, presence taken at: " + entrance);
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
}