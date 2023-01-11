package Control;

import Database.AbstentionRequest;
import Database.Employee;
import Database.Notification;
import Database.Database;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AbstentionRequestController {

    private Employee employee;
    private Database DBMS;

    private TextField description;
    private TextField receiver;
    private RadioButton typeVacation, typeParentalLeave, typeSickLeave, typeStrike;
    private TextField startYear, startMonth, startDay;
    private TextField endYear, endMonth, endDay;


    private Button HomePage;
    private Button confirmRequest;


    /**
     * Constructor to connect the user of the app
     *
     * @param employee Employee object
     */
    public AbstentionRequestController(Employee employee) {
        DBMS = new Database();

        employee = new Employee();
        employee.copy(employee);
    }

    public void sendAbstentionRequest() {
        if (!checkFieldsValidity()) {
            /* Insert error message */
        } else {
            sendAbstentionRequest();
        }
    }


    private boolean checkFieldsValidity() {
        boolean emptyFields = !description.getText().equals("") | !receiver.getText().equals("");
        boolean radioBtnNotSelected = !typeVacation.isSelected() && !typeParentalLeave.isSelected() && !typeSickLeave.isSelected() && !typeStrike.isSelected();

        if (emptyFields | radioBtnNotSelected) {
            return false;
        }

        boolean yearIncorrect = Integer.parseInt(endYear.getText()) > Integer.parseInt(startYear.getText());
        boolean monthIncorrect = Integer.parseInt(endMonth.getText()) > Integer.parseInt(startMonth.getText());
        boolean dayIncorrect = Integer.parseInt(endDay.getText()) > Integer.parseInt(startDay.getText());

        if (yearIncorrect) {
            return false;
        } else if (monthIncorrect) {
            return false;
        } else if (dayIncorrect) {
            return false;
        }

        return true;
    }

    private void sendRequest() {
        String title = "Abstention Request From " + employee.getName() + " " + employee.getSurname();
        String description = this.description.getText();

        /* Create a Notification and don't increment the global counter, the receiver is the Employer */
        Notification notification = new Notification(title, description, 1, employee.getID(), Integer.MAX_VALUE);

        String startDate = startYear.getText() + "/" + startMonth.getText() + "/" + startDay.getText();
        String endDate = endYear.getText() + "/" + endMonth.getText() + "/" + endDay.getText();

        int type = 1;

        if (typeVacation.isSelected()) {
            type = 1;
        } else if (typeParentalLeave.isSelected()) {
            type = 2;
        } else if (typeSickLeave.isSelected()) {
            type = 3;
        } else if (typeStrike.isSelected()) {
            type = 4;
        }

        DBMS.addAbstentionRequest(new AbstentionRequest(notification, false, type, startDate, endDate));
    }
}
