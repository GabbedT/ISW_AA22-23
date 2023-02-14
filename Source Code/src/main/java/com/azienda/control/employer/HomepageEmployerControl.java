package com.azienda.control.employer;

import com.azienda.AccountApplication;
import com.azienda.database.DatabaseControl;
import com.azienda.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageEmployerControl implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private Button button11;

    @FXML
    private Label ProfileName;

    @FXML
    private Button buttonAddEmployee;

    @FXML
    private Button ChangePasswordButton;

    @FXML
    private Button LogoutButton;

    @FXML
    private ImageView ImageNotifications;

    @FXML
    private Button NotificationsButton;

    @FXML
    private Button ProfileButton;

    @FXML
    private Button SearchEmployee;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseControl DBMS = new DatabaseControl();
        User user = DBMS.getUser(DBMS.getLoggedUserID());

        ProfileName.setText(user.getName().toUpperCase() + " " + user.getSurname().toUpperCase());
    }


    public void userLogOut(ActionEvent event) throws IOException {
        AccountApplication m = new AccountApplication();
        m.changeScene("control/shared/LoginModule.fxml");
    }

    public void View(ActionEvent event) throws IOException{
        AccountApplication m = new AccountApplication();
        m.changeScene("control/shared/NotificationListModule.fxml");
    }

    public void userAddEmployee(ActionEvent event) throws IOException {
        AccountApplication m = new AccountApplication();
        m.changeScene("control/employer/HireEmployeeModule.fxml");
    }

    @FXML
    void Search(ActionEvent event) throws IOException{
        AccountApplication m = new AccountApplication();
        m.changeScene("control/employer/SearchEmployeeModule.fxml");
    }

    @FXML
    void Change(ActionEvent event) throws IOException {
        AccountApplication m = new AccountApplication();
        m.changeScene("control/shared/ModifyPasswordModule.fxml");

    }

    @FXML
    void ViewSchedule(ActionEvent event) throws IOException {
        AccountApplication m = new AccountApplication();
        m.changeScene("control/employer/CompleteShiftModule.fxml");

    }
}
