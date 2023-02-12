package com.example.azienda;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeShift implements Initializable {

    public static class Turnation {

        private String MondayPeriod = "              / ";

        public String getMondayPeriod() {
            return MondayPeriod;
        }

        private String TuesdayPeriod = "              / ";

        public String getTuesdayPeriod() {
            return TuesdayPeriod;
        }

        private String WednesdayPeriod = "              / ";

        public String getWednesdayPeriod() {
            return WednesdayPeriod;
        }

        private String ThursdayPeriod = "              / ";

        public String getThursdayPeriod() {
            return ThursdayPeriod;
        }

        private String FridayPeriod = "              / ";

        public String getFridayPeriod() {
            return FridayPeriod;
        }

        private String SaturdayPeriod = "              / ";

        public String getSaturdayPeriod() {
            return SaturdayPeriod;
        }

        private String SundayPeriod;

        public String getSundayPeriod() {
            return SundayPeriod;
        }


        private String MondayService;

        public String getMondayService() {
            return MondayService;
        }

        private String TuesdayService;

        public String getTuesdayService() {
            return TuesdayService;
        }

        private String WednesdayService;

        public String getWednesdayService() {
            return WednesdayService;
        }

        private String ThursdayService;

        public String getThursdayService() {
            return ThursdayService;
        }

        private String FridayService;

        public String getFridayService() {
            return FridayService;
        }

        private String SaturdayService;

        public String getSaturdayService() {
            return SaturdayService;
        }

        private String SundayService;

        public String getSundayService() {
            return SundayService;
        }

        public Turnation(Shift[] shift, Employee employee) {
            if (shift.length > 7) {
                System.err.println("Too long!");
            }

            for (int i = 0; i < shift.length; ++i) {
                switch (shift[i].getDay()) {
                    case 1:
                        this.MondayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        this.MondayService = "      " + shift[i].getService();
                        break;

                    case 2:
                        this.TuesdayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        this.TuesdayService = "      " + shift[i].getService();
                        break;

                    case 3:
                        this.WednesdayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        this.WednesdayService = "      " + shift[i].getService();
                        break;

                    case 4:
                        this.ThursdayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        this.ThursdayService = "      " + shift[i].getService();
                        break;

                    case 5:
                        this.FridayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        this.FridayService = "      " + shift[i].getService();
                        break;

                    case 6:
                        this.SaturdayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        this.SaturdayService = "      " + shift[i].getService();
                        break;

                    case 7:
                        this.SundayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        this.SundayService = "      " + shift[i].getService();
                        break;

                    default:
                        System.err.println("DAY OUT OF RANGE!");
                        break;
                }
            }
        }
    }

    @FXML
    private TableColumn<Turnation, String> FridayColumn;

    @FXML
    private TableColumn<Turnation, String> MondayColumn;

    @FXML
    private TableColumn<Turnation, String> SaturdayColumn;

    @FXML
    private TableView<Turnation> ShiftTable;

    @FXML
    private TableColumn<Turnation, String> SundayColumn;

    @FXML
    private TableColumn<Turnation, String> ThursdayColumn;

    @FXML
    private TableColumn<Turnation, String> TuesdayColumn;

    @FXML
    private TableColumn<Turnation, String> WednesdayColumn;

    @FXML
    private TableColumn<Turnation, String> ServiceMonday;

    @FXML
    private TableColumn<Turnation, String> ServiceTuesday;

    @FXML
    private TableColumn<Turnation, String> ServiceWednesday;

    @FXML
    private TableColumn<Turnation, String> ServiceThursday;

    @FXML
    private TableColumn<Turnation, String> ServiceFriday;

    @FXML
    private TableColumn<Turnation, String> ServiceSaturday;

    @FXML
    private TableColumn<Turnation, String> ServiceSunday;

    @FXML
    private Button buttonHomePage;

    @FXML
    void userHomePage(ActionEvent event) throws IOException {
        HelloApplication n = new HelloApplication();
        n.changeScene("homepageDipendente.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HelloApplication n = new HelloApplication();
        Database DBMS = new Database();

        Shift[] weeklyShift = new Shift[5];

        /* Get all the shift days of the employee in the current week */
        Shift[] dayList = DBMS.getWeekShift(SystemControl.weekOfTrimester(true), DBMS.getLoggedUserID());
        System.out.println(dayList.length);

        Turnation employeeTurnation = new Turnation(dayList, DBMS.getEmployee(DBMS.getUser(DBMS.getLoggedUserID())));

        ObservableList<Turnation> completeEmployeeTurnation = FXCollections.observableArrayList(employeeTurnation);

        MondayColumn.setCellValueFactory(new PropertyValueFactory<>("MondayPeriod"));
        TuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("TuesdayPeriod"));
        WednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("WednesdayPeriod"));
        ThursdayColumn.setCellValueFactory(new PropertyValueFactory<>("ThursdayPeriod"));
        FridayColumn.setCellValueFactory(new PropertyValueFactory<>("FridayPeriod"));
        SaturdayColumn.setCellValueFactory(new PropertyValueFactory<>("SaturdayPeriod"));
        SundayColumn.setCellValueFactory(new PropertyValueFactory<>("SundayPeriod"));

        ServiceMonday.setCellValueFactory(new PropertyValueFactory<>("MondayService"));
        ServiceTuesday.setCellValueFactory(new PropertyValueFactory<>("TuesdayService"));
        ServiceWednesday.setCellValueFactory(new PropertyValueFactory<>("WednesdayService"));
        ServiceThursday.setCellValueFactory(new PropertyValueFactory<>("ThursdayService"));
        ServiceFriday.setCellValueFactory(new PropertyValueFactory<>("FridayService"));
        ServiceSaturday.setCellValueFactory(new PropertyValueFactory<>("SaturdayService"));
        ServiceSunday.setCellValueFactory(new PropertyValueFactory<>("SundayService"));

        ShiftTable.setItems(completeEmployeeTurnation);
    }
}

