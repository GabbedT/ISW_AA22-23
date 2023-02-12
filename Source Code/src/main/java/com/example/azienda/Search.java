package com.example.azienda;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;



public class Search implements Initializable {
        @FXML
        private TableView<User> tableViewWork;
        @FXML
        private TableColumn<User, Integer> EmpIDColumn;
        @FXML
        private TableColumn<User, String> SurnameColumn;
        @FXML
        private TableColumn<User, String> NameColumn;
        @FXML
        private TextField searchBox;
        @FXML
        private Label label;
        @FXML
        private Button goBackButton;

        @FXML
        private Button ConfirmButton;

        ObservableList<User> searchEmployeeObservableList;

        public void initialize(URL url, ResourceBundle resource){
                Database DBMS = new Database();
                ArrayList<User> list = new ArrayList<>();

                /* Retrieve the entire database */
                for (int i = 2; i <= DBMS.getMaximumEmployeeID(); ++i) {
                        list.add(DBMS.getUser(i));
                }

                searchEmployeeObservableList = FXCollections.observableArrayList(list);

                EmpIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
                SurnameColumn.setCellValueFactory(new PropertyValueFactory<>("Surname"));
                NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

                tableViewWork.setItems(searchEmployeeObservableList);

                FilteredList<User> filteredData = new FilteredList<>(searchEmployeeObservableList, b -> true);

                searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
                        filteredData.setPredicate(searchEmployee ->{

                                /* If no search value then display all records or whatever records it current have. no changes. */
                                if(newValue.isEmpty() || newValue.isBlank()) {
                                        return true;
                                }

                                String searchKeyword = newValue.toLowerCase();

                                if (searchKeyword.contains(" ")) {
                                        String nameEmp = searchKeyword.substring(0, searchKeyword.indexOf(" ") - 1);
                                        String surnameEmp = searchKeyword.substring(searchKeyword.indexOf(" ") + 1);

                                        System.out.println(nameEmp + " " + surnameEmp);

                                        if ((searchEmployee.getName().toLowerCase().contains(nameEmp)) && (searchEmployee.getSurname().toLowerCase().contains(surnameEmp))) {
                                                return true;
                                        } else {
                                                return false;
                                        }
                                } else {
                                        if (searchEmployee.getName().toLowerCase().contains(searchKeyword)) {
                                                return true; // Means we found a match in Name
                                        } else if (searchEmployee.getSurname().toLowerCase().contains(searchKeyword)) {
                                                return true; // Means we found a match in Name
                                        } else {
                                                return false; // no match found
                                        }
                                }
                        });
                });

                SortedList<User> sortedData = new SortedList<>(filteredData);

                //Bind sorted result with Table View
                sortedData.comparatorProperty().bind(tableViewWork.comparatorProperty());

                //Apply filtered and sorted data to the Table View
                tableViewWork.setItems(sortedData);
        }

        @FXML
        public void GoHome(ActionEvent event) throws IOException {
                HelloApplication n = new HelloApplication();
                n.changeScene("homepageDatore.fxml");
        }

        @FXML
        void ButtonConfirm(ActionEvent event) {
                Database DBMS = new Database();

                /* Retrieve an employee based on the corresponding user */
                Employee selectedEmployee = DBMS.getEmployee(tableViewWork.getSelectionModel().getSelectedItem());
                DBMS.modifySearchEmployeeID(selectedEmployee.getID());
                System.out.println(selectedEmployee.toString());

                try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Licenzia.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.show();
                } catch (Exception e){
                        e.printStackTrace();
                }
        }
}












