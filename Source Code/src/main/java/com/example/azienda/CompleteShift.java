package com.example.azienda;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CompleteShift extends Application implements Initializable {

    @Override
    public void start(Stage stage) throws Exception {

    }

    public static class Turnation {
        private String MondayPeriod = "                 / ";

        public String getMondayPeriod() {
            return MondayPeriod;
        }


        private String TuesdayPeriod = "                 / ";

        public String getTuesdayPeriod() {
            return TuesdayPeriod;
        }


        private String WednesdayPeriod = "                 / ";

        public String getWednesdayPeriod() {
            return WednesdayPeriod;
        }


        private String ThursdayPeriod = "                 / ";

        public String getThursdayPeriod() {
            return ThursdayPeriod;
        }


        private String FridayPeriod = "                 / ";

        public String getFridayPeriod() {
            return FridayPeriod;
        }


        private String SaturdayPeriod = "                 / ";

        public String getSaturdayPeriod() {
            return SaturdayPeriod;
        }


        private String SundayPeriod = "                 / ";

        public String getSundayPeriod() {
            return SundayPeriod;
        }


        private final String service;
        private final String employee;

        public String getService() {
            return service;
        }

        public String getEmployee() {
            return employee;
        }

        public Turnation(Shift[] shift, Employee employee) {
            if (shift.length > 7) {
                System.err.println("Too long!");
            }

            for (int i = 0; i < shift.length; ++i) {
                if (shift[i].getExpectedEntrance().isEmpty() || shift[i].getExpectedExit().isEmpty()) {
                    switch (shift[i].getDay()) {
                        case 1:
                            this.MondayPeriod = "              - ";
                            break;

                        case 2:
                            this.TuesdayPeriod = "              - ";
                            break;

                        case 3:
                            this.WednesdayPeriod = "              - ";
                            break;

                        case 4:
                            this.ThursdayPeriod = "              - ";
                            break;

                        case 5:
                            this.FridayPeriod = "              - ";
                            break;

                        case 6:
                            this.SaturdayPeriod = "              - ";
                            break;

                        case 7:
                            this.SundayPeriod = "              - ";
                            break;

                        default:
                            System.err.println("DAY OUT OF RANGE!");
                            break;
                    }
                } else {
                    switch (shift[i].getDay()) {
                        case 1:
                            this.MondayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        break;

                        case 2:
                            this.TuesdayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        break;

                        case 3:
                            this.WednesdayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        break;

                        case 4:
                            this.ThursdayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        break;

                        case 5:
                            this.FridayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        break;

                        case 6:
                            this.SaturdayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        break;

                        case 7:
                            this.SundayPeriod = "    " + shift[i].getExpectedEntrance() + " - " + shift[i].getExpectedExit();
                        break;

                        default:
                            System.err.println("DAY OUT OF RANGE!");
                        break;
                    }
                }
            }

            this.service = "       " + employee.getService();
            this.employee = employee.getSurname() + " " + employee.getName();
        }
    }

    @FXML
    private TableColumn<Turnation, String> EmployeeColumn;

    @FXML
    private TableColumn<Turnation, String> FridayColumn;

    @FXML
    private TableColumn<Turnation, String> MondayColumn;

    @FXML
    private TableColumn<Turnation, String> SaturdayColumn;

    @FXML
    private TableColumn<Turnation, Character> ServiceColumn;

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
    private Button buttonHomePage;

    @FXML
    public void Home(ActionEvent event) throws IOException {
        HelloApplication n = new HelloApplication();
        n.changeScene("homepageDatore.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HelloApplication n = new HelloApplication();
        Database DBMS = new Database();

        ArrayList<Turnation> turnationList = new ArrayList<>();
        Shift[] weeklyShift;

        for (int i = 2; i < DBMS.getMaximumEmployeeID(); ++i) {
            /* Get all the shift days of the employees in the current week */
            weeklyShift = DBMS.getWeekShift(SystemControl.weekOfTrimester(true), i);

            if (weeklyShift != null) {
                turnationList.add(new Turnation(weeklyShift, DBMS.getEmployee(DBMS.getUser(i))));
            }
        }

        ObservableList<Turnation> completeTurnation = FXCollections.observableArrayList(turnationList);

        MondayColumn.setCellValueFactory(new PropertyValueFactory<>("MondayPeriod"));
        TuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("TuesdayPeriod"));
        WednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("WednesdayPeriod"));
        ThursdayColumn.setCellValueFactory(new PropertyValueFactory<>("ThursdayPeriod"));
        FridayColumn.setCellValueFactory(new PropertyValueFactory<>("FridayPeriod"));
        SaturdayColumn.setCellValueFactory(new PropertyValueFactory<>("SaturdayPeriod"));
        SundayColumn.setCellValueFactory(new PropertyValueFactory<>("SundayPeriod"));

        ServiceColumn.setCellValueFactory(new PropertyValueFactory<>("Service"));
        EmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("Employee"));

        ShiftTable.setItems(completeTurnation);
    }



}

