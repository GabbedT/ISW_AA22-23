package com.example.azienda;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class homepageImpiegato {


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
                HelloApplication m = new HelloApplication();
                m.changeScene("ModifyPassword.fxml");

        }


        @FXML
        void userLogOut(ActionEvent event) throws IOException {
                HelloApplication m = new HelloApplication();
                m.changeScene("Login.fxml");

        }

        @FXML
        void userNotificationEmployee(ActionEvent event) throws IOException {
                HelloApplication m = new HelloApplication();
                m.changeScene("Notifications.fxml");

        }

        @FXML
        void userRemotePresence(ActionEvent event) throws IOException {
                HelloApplication m = new HelloApplication();
                m.changeScene("RilevaPresenzaRemot.fxml");
        }

        @FXML
        void userRequestAbstention(ActionEvent event) throws IOException {
                HelloApplication m = new HelloApplication();
                m.changeScene("RichiestaAstensione.fxml");

        }

        @FXML
        void userViewShifts(ActionEvent event) throws IOException {
                HelloApplication m = new HelloApplication();
                m.changeScene("TurnazioneDipendente.fxml");

        }

        @FXML
        void ShowProfile(ActionEvent event) throws IOException {
                HelloApplication m = new HelloApplication();
                m.changeScene("UserInformation.fxml");

        }

}
