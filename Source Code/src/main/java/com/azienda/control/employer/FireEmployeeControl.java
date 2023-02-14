package com.azienda.control.employer;

import com.azienda.AccountApplication;
import com.azienda.database.DatabaseControl;
import com.azienda.entity.Employee;
import com.azienda.entity.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FireEmployeeControl implements Initializable {

    @FXML
    private TableColumn<Employee, Integer> AgeColumn;

    @FXML
    private TableColumn<Employee, Date> DateOfBirthColumn;

    @FXML
    private Button FireButton;

    @FXML
    private Label WrongInput;
    @FXML
    private Button goBackButton;

    @FXML
    private Label FireError;

    @FXML
    private Label UserData;

    @FXML
    private TableColumn<Employee, Character> GenderColumn;

    @FXML
    private TableColumn<Employee, Character> ServiceColumn;

    @FXML
    private TableColumn<Employee, String> MailColumn;

    @FXML
    private TableColumn<Employee, Integer> SalaryColumn;

    @FXML
    private TextArea TextReason;

    @FXML
    private TableColumn<Employee, Integer> WorkHoursColumn;

    @FXML
    private TableView<Employee> tableViewFire;


    private Employee employee;

    public void setSelectedEmployee(Employee emp) {
        this.employee.copy(emp);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseControl DBMS = new DatabaseControl();
        this.employee = DBMS.getEmployee(DBMS.getUser(DBMS.getSearchEmployeeID()));

        UserData.setText(this.employee.getName().toUpperCase() + " " + this.employee.getSurname().toUpperCase());

        ArrayList<Employee> list = new ArrayList<>(1);
        list.add(this.employee);
        ObservableList<Employee> searchEmployeeObservableList = FXCollections.observableArrayList(list);

        AgeColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        DateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        GenderColumn.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        MailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        SalaryColumn.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        WorkHoursColumn.setCellValueFactory(new PropertyValueFactory<>("FinalWorkHours"));
        ServiceColumn.setCellValueFactory(new PropertyValueFactory<>("Service"));

        tableViewFire.setItems(searchEmployeeObservableList);
    }
    @FXML
    void Fire(ActionEvent event) throws IOException {
        AccountApplication n = new AccountApplication();
        DatabaseControl DBMS = new DatabaseControl();

        if (TextReason.getText().isEmpty()) {
            showErrorPanel("Insert a valid reason!");
            return;
        } else {
            Notification fireNotification = new Notification("Dismissal Letter", TextReason.getText(), this.employee.getID(), 1);
            if (DBMS.addNotification(fireNotification)) {
                DBMS.modifyGlobalNotificationID(DBMS.getGlobalNotificationID() + 1);
            } else {
                showErrorPanel("Error in DatabaseControl!");
                return;
            }
        }

        if (DBMS.pushEmployeeFire(this.employee.getID())) {
            ShowInfoPanel("Operation completed, employee will be fired in: " + (DBMS.getTrimester()[1].toString()));
        } else {
            showErrorPanel("Error, cannot fire the employee two times!");
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

    public void ShowInfoPanel(String msg) {
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
    public void Home(ActionEvent event) throws IOException {
        AccountApplication n = new AccountApplication();

        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }


}

