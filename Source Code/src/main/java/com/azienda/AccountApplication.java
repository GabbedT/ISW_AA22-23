package com.azienda;

import com.azienda.control.SystemControl;
import com.azienda.database.DatabaseControl;
import com.azienda.entity.AbstentionRequest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AccountApplication extends javafx.application.Application {

    private static Stage stg;


    @Override
    public void start(Stage primaryStage) throws IOException {
        /* Open the application GUI */
        stg = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(AccountApplication.class.getResource("control/shared/LoginModule.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
        primaryStage.setTitle("Account");
        primaryStage.setScene(scene);
        primaryStage.show();

        DatabaseControl DBMS = new DatabaseControl();
        char service = 'A';
        Integer[][] listEmp = new Integer[4][];

        /* Check if the trimester has ended */
        if (SystemControl.checkEndTrimester()) {
            /* Set a new trimester */
            SystemControl.updateTrimester();

            /* Validate accounts */
            if (!DBMS.validateHiringAccounts()) {
                System.err.println("Error while validating accounts!");
            }

            /* Delete accounts and notifications associated */
            if (!DBMS.deleteFiredAccounts()) {
                System.err.println("Error while removing accounts!");
            }

            /* Clear the turnation */
            if (!DBMS.deleteShift()) {
                System.err.println("Error while removing turnation!");
            }

            /* Clear all the abstention requests that are not
             * vacation requests */
            if (!DBMS.clearAbstentionRequest()) {
                System.err.println("Error while removing requests!");
            }

            /* Retrieve all the employees for each service */
            for (char i = 0; i < 4; ++i) {
                DBMS.getEmployee(service).toArray(listEmp[i]);

                for (int j = 0; j < listEmp[i].length; ++j) {
                    System.out.println(listEmp[i][j]);
                }
                ++service;
            }

            /* Generate schedule */
            SystemControl.generateSchedule(listEmp);

            /* Fill the shifts */
            for (char i = 0; i < 4; ++i) {
                for (int j = 0; j < listEmp[i].length; ++j) {
                    SystemControl.fillShift(listEmp[i][j]);
                }
            }

            /* Check for vacations requests */
            ArrayList<AbstentionRequest> requestList = DBMS.getAbstentionRequest(1, true);

            for (int i = 0; i < requestList.size(); ++i) {
                SystemControl.changeShift(requestList.get(i));
            }
        } else {
            System.out.println("No operations needed");
        }

    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}