package com.azienda.control.employee;

import com.azienda.AccountApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomepageEmployeeControl {


        @FXML
        private Label ProfileName;

        @FXML
        private Label accountBack;

        @FXML
        private Button buttonChangePassword;

        @FXML
        private Button buttonLogout;

        @FXML
        private Button ProfileButton;

        @FXML
        private Button buttonNotifications;

        @FXML
        private Button buttonRemotePresence;

        @FXML
        private Button buttonRequestAbsence;

        @FXML
        private Button buttonRequestAbstention;

        @FXML
        private Button buttonViewShifts;

        @FXML
        private Button buttoneTimePreference;


        @FXML
        void userChangePassword(ActionEvent event) throws IOException {
                AccountApplication m = new AccountApplication();
                m.changeScene("control/shared/ModifyPasswordModule.fxml");

        }


        @FXML
        void userLogOut(ActionEvent event) throws IOException {
                AccountApplication m = new AccountApplication();
                m.changeScene("control/shared/LoginModule.fxml");

        }

        @FXML
        void userNotificationEmployee(ActionEvent event) throws IOException {
                AccountApplication m = new AccountApplication();
                m.changeScene("control/shared/NotificationListModule.fxml");

        }

        @FXML
        void userRemotePresence(ActionEvent event) throws IOException {
                AccountApplication m = new AccountApplication();
                m.changeScene("control/employee/RemotePresenceModule.fxml");
        }

        @FXML
        void userRequestAbstention(ActionEvent event) throws IOException {
                AccountApplication m = new AccountApplication();
                m.changeScene("control/employee/RequestAbstentionModule.fxml");

        }

        @FXML
        void userViewShifts(ActionEvent event) throws IOException {
                AccountApplication m = new AccountApplication();
                m.changeScene("control/employee/EmployeeShiftModule.fxml");

        }

        @FXML
        void ShowProfile(ActionEvent event) throws IOException {
                AccountApplication m = new AccountApplication();
                m.changeScene("control/employee/UserInfoModule.fxml");

        }

}
