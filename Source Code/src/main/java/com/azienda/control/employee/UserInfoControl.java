package com.azienda.control.employee;

import com.azienda.AccountApplication;
import com.azienda.database.DatabaseControl;
import com.azienda.entity.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoControl implements Initializable {

    @FXML
    private Label Age;

    @FXML
    private Label CurrentSalary;

    @FXML
    private Label DateOfBirth;

    @FXML
    private Label Email;

    @FXML
    private Label OvertimeHours;

    @FXML
    private Label Gender;

    @FXML
    private Label InfoName;

    @FXML
    private Label Name;

    @FXML
    private Label Salary;

    @FXML
    private Label Surname;

    @FXML
    private Label WorkHours;

    @FXML
    private Button buttonHomePage;

    @FXML
    private Label Service;

    @FXML
    void userHomePage(ActionEvent event) throws IOException {
        AccountApplication n = new AccountApplication();
        n.changeScene("control/employee/HomepageEmployeeModule.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseControl DBMS = new DatabaseControl();

        Employee loggedEmp = DBMS.getEmployee(DBMS.getUser(DBMS.getLoggedUserID()));

        InfoName.setText(loggedEmp.getName().toUpperCase() + " " + loggedEmp.getSurname().toUpperCase());

        Name.setText(loggedEmp.getName());
        Surname.setText(loggedEmp.getSurname());
        Email.setText(loggedEmp.getEmail());
        Gender.setText(String.valueOf(loggedEmp.getGender()));
        Age.setText(String.valueOf(loggedEmp.getAge()));
        Salary.setText(String.valueOf(loggedEmp.getSalary()));
        CurrentSalary.setText(String.valueOf(loggedEmp.computeCurrentSalary()));
        DateOfBirth.setText(loggedEmp.getDateOfBirth().toString());
        WorkHours.setText(String.valueOf(loggedEmp.getFinalWorkHours()));
        OvertimeHours.setText(String.valueOf(loggedEmp.getOvertimeHours()));
        Service.setText(String.valueOf(loggedEmp.getService()));
    }
}

