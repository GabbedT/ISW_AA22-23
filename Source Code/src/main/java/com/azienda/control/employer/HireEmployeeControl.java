package com.azienda.control.employer;


import com.azienda.AccountApplication;
import com.azienda.control.SystemControl;
import com.azienda.database.DatabaseControl;
import com.azienda.entity.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class HireEmployeeControl {
    @FXML
    private Label WrongInput;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField email;

    @FXML
    private TextField gender;

    @FXML
    private TextField service;

    @FXML
    private Button goBackButton;

    @FXML
    private Button ConfirmButton;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField salary;

    @FXML
    public void GoHome(ActionEvent event) throws IOException {
        AccountApplication n = new AccountApplication();
        n.changeScene("control/employer/HomepageEmployerModule.fxml");
    }



    @FXML
    public void ButtonConfirm(ActionEvent event) throws IOException {
        AccountApplication n = new AccountApplication();

        String empName = name.getText();
        if (empName.isEmpty()) {
            showErrorPanel("Empty employee name!");
            return;
        }
        /* Make the first character capital */
        char firstChar = Character.toUpperCase(empName.charAt(0));
        empName = firstChar + empName.substring(1);

        String empSurname = surname.getText();
        if (empSurname.isEmpty()) {
            showErrorPanel("Empty employee surname!");
            return;
        }
        /* Make the first character capital */
        firstChar = Character.toUpperCase(empSurname.charAt(0));
        empSurname = firstChar + empSurname.substring(1);


        String empEmail = email.getText();
        if (empEmail.isEmpty()) {
            showErrorPanel("Empty employee email field!");
            return;
        } else if (!empEmail.contains("@")) {
            showErrorPanel("Missing '@' in email field!");
            return;
        }


        /* Check gender field validity */
        char empGender = gender.getText().toUpperCase().charAt(0);

        if ((empGender != 'M' && empGender != 'F') || (service.getText().length() != 1)) {
            showErrorPanel("Wrong gender format!");
            return;
        }


        char empService = service.getText().toUpperCase().charAt(0);

        if (service.getText().length() != 1) {
            showErrorPanel("Wrong service format!");
            return;
        } else {
            switch (empService) {
                case 'A', 'B', 'C', 'D': break;

                default: showErrorPanel("Wrong service format!");
                return;
            }
        }

        /* Check salary field validity */
        int empSalary = Integer.parseInt(salary.getText());

        if (!isNumeric(salary.getText())) {
            showErrorPanel("Wrong salary format!");
            return;
        } else if ((float)(empSalary / 160) < 9.0F) {
            showErrorPanel("Minimum hourly salary is 9 €/h!");
            return;
        }

        Date empDate = Date.valueOf(datePicker.getValue());
        int empAge = Period.between(datePicker.getValue(), LocalDate.now()).getYears();

        if (empAge < 18) {
            showErrorPanel("Cannot hire an underage!");
            return;
        }

        Employee employee = new Employee(empName, empSurname, empEmail, SystemControl.generatePassword(), empGender, empAge, empDate, empService, empSalary, 480);


        DatabaseControl DBMS = new DatabaseControl();
        if (DBMS.addEmployee(employee) && DBMS.pushEmployeeHire(employee.getID())) {
            /* Increment global user ID */
            DBMS.modifyGlobalUserID(DBMS.getGlobalUserID() + 1);

            showInfoPanel("Operation completed, employee: "
                                         + employee.getSurname() + " " + employee.getName() + " inserted on DatabaseControl!\n"
                                         + "ID: " + employee.getID() + "\nPassword: " + employee.getPassword());
            name.clear();
            surname.clear();
            email.clear();
            gender.clear();
            salary.clear();
            service.clear();
            datePicker.setValue(null);
        } else {
            showErrorPanel("Error while adding the employee to database!");
        }
    }

    private void Conf() throws IOException {
        AccountApplication m = new AccountApplication();

    }

    private boolean isNumeric(String str) {
        int value;

        if (str == null) {
            return false;
        } else {
            try {
                value = Integer.parseInt(str);
                return true;
            } catch (NumberFormatException exc) {
                exc.printStackTrace();
                return false;
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

    @FXML
    void NameText(ActionEvent event) throws IOException {
        name.setStyle("-fx-border-color:#00ced1");
    }
}