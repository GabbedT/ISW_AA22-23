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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class ExitControl {

    @FXML
    private Label exit;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    void confirmExit(ActionEvent event) {
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

        String exit = LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();
        Shift currentShift = DBMS.getShift(LocalDateTime.now().getDayOfWeek().getValue(), SystemControl.weekOfTrimester(true), usr.getID());

        /* Check if not in time (exit < expectedExit) */
        if (SystemControl.checkStartTime(currentShift.getExpectedExit(), exit)) {
            showErrorPanel("Early exit not allowed!");
        } else {
            if (!DBMS.modifyShiftExit(exit, LocalDateTime.now().getDayOfWeek().getValue(), SystemControl.weekOfTrimester(true), usr.getID())) {
                showErrorPanel("Error while accessing into database");
                return;
            }
        }

        showInfoPanel("Operation completed, exit taken at: " + exit);
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
