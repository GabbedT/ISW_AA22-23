package com.azienda.control.employee;

import com.azienda.database.DatabaseControl;
import com.azienda.entity.Employee;
import com.azienda.AccountApplication;
import com.azienda.entity.AbstentionRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;


public class RequestAbstentionControl {

    @FXML
    private DatePicker DateFrom;

    @FXML
    private DatePicker DateTo;

    @FXML
    private RadioButton ParentalLeaveButton;

    @FXML
    private TextArea Reason;

    @FXML
    private RadioButton SickLeaveButton;

    @FXML
    private RadioButton StrikeButton;

    @FXML
    private RadioButton VacationButton;

    @FXML
    private Button buttonConfirmChangePassword;

    @FXML
    private Button buttonHomePage1;

    @FXML
    private Label WrongInput;



    @FXML
    void ButtonConfirm(ActionEvent event) throws IOException {
        Send();

    }

    @FXML
    void userHomePage(ActionEvent event)throws IOException {
        AccountApplication m = new AccountApplication();
        m.changeScene("control/employee/HomepageEmployeeModule.fxml");
    }


    private void Send() throws IOException{
        DatabaseControl DBMS = new DatabaseControl();
        Employee employee = DBMS.getEmployee(DBMS.getUser(DBMS.getLoggedUserID()));

        String reqReason = Reason.getText();
        if (Reason.getText().isEmpty()) {
            showErrorPanel("Insert a valid reason description!");
            return;
        }

        Date reqEnd = Date.valueOf(DateTo.getValue());
        Date reqStart = Date.valueOf(DateFrom.getValue());
        if ((DateFrom.getValue() == null) || (DateTo.getValue() == null)) {
            showErrorPanel("Insert a valid date!");
            return;
        } else if (reqStart.after(reqEnd)) {
            showErrorPanel("The abstention end must be after the abstention start!");
            return;
        } else if (reqStart.before(new Date(System.currentTimeMillis()))) {
            showErrorPanel("Select a date after the current one!");
            return;
        }

        int reqType;
        if (ParentalLeaveButton.isSelected()) {
            reqType = AbstentionRequest.AbstentionType.PARENTAL_LEAVE.getValue();

            /* Deselect the other buttons */
            VacationButton.setSelected(false);
            SickLeaveButton.setSelected(false);
            StrikeButton.setSelected(false);
        } else if (VacationButton.isSelected()) {
            reqType = AbstentionRequest.AbstentionType.VACATION.getValue();

            if (reqStart.compareTo(DBMS.getTrimester()[1]) > 0) {
                /* Check if the request start date is later than the end of the
                 * current trimester */
                showErrorPanel("The start of the abstention must happens later than the end of the current trimester!");
                return;
            }

            ArrayList<AbstentionRequest> reqList = DBMS.getAbstentionRequest(DBMS.getLoggedUserID(), false);
            Date compStart, compEnd;

            if (reqList != null) {
                for (int i = 0; i < reqList.size(); ++i) {
                    compStart = reqList.get(i).getStartAbstention();
                    compEnd = reqList.get(i).getEndAbstention();

                    if ((reqStart.before(compEnd) && reqStart.after(compStart)) || (compStart.before(reqEnd) && compStart.after(reqStart))) {
                        /* Check if the start of the abstention is in the period of another
                         * abstention request (of the same user) */
                        showErrorPanel("The selected abstention period overlap with another one!");
                        return;
                    } else if ((reqEnd.before(compEnd) && reqEnd.after(compStart)) || (compEnd.before(reqEnd) && compEnd.after(reqStart))) {
                        /* Check if the end of the abstention is in the period of another
                         * abstention request (of the same user) */
                        showErrorPanel("The selected abstention period overlap with another one!");
                        return;
                    } else if (reqStart.equals(compStart) && reqEnd.equals(compEnd)) {
                        /* Check if the two periods are the same */
                        showErrorPanel("The selected abstention period overlap with another one!");
                        return;
                    }
                }
            }

            /* Deselect the other buttons */
            ParentalLeaveButton.setSelected(false);
            SickLeaveButton.setSelected(false);
            StrikeButton.setSelected(false);
        } else if (SickLeaveButton.isSelected()) {
            reqType = AbstentionRequest.AbstentionType.SICK_LEAVE.getValue();

            /* Deselect the other buttons */
            ParentalLeaveButton.setSelected(false);
            VacationButton.setSelected(false);
            StrikeButton.setSelected(false);
        } else if (StrikeButton.isSelected()) {
            reqType = AbstentionRequest.AbstentionType.STRIKE.getValue();

            /* Deselect the other buttons */
            ParentalLeaveButton.setSelected(false);
            VacationButton.setSelected(false);
            SickLeaveButton.setSelected(false);
        } else {
            showErrorPanel("Select a valid reason!");
            return;
        }

        String reqTitle = "Abstention request";

        if (DBMS.addAbstentionRequest(new AbstentionRequest(reqTitle, reqReason, 1, DBMS.getLoggedUserID(), false, reqType, reqStart, reqEnd))) {
            /* Increment global notification ID */
            DBMS.modifyGlobalNotificationID(DBMS.getGlobalNotificationID() + 1);

            showInfoPanel("Operation completed!");
            DateTo.setValue(null);
            DateFrom.setValue(null);
            ParentalLeaveButton.setSelected(false);
            VacationButton.setSelected(false);
            SickLeaveButton.setSelected(false);
            StrikeButton.setSelected(false);
            Reason.clear();
        } else {
            try {
                showErrorPanel("Error while sending abstention request!");
            } catch (Exception e){
                e.printStackTrace();
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
