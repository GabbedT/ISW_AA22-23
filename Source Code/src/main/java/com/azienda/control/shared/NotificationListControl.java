package com.azienda.control.shared;

import com.azienda.AccountApplication;
import com.azienda.database.DatabaseControl;
import com.azienda.entity.AbstentionRequest;
import com.azienda.entity.Notification;
import com.azienda.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NotificationListControl implements Initializable {

    public static class NotificationTable {
        private final int notificationID;

        public int getNotificationID() {
            return notificationID;
        }

        private final String type;

        public String getType() {
            return type;
        }

        private final String title;

        public String getTitle() {
            return title;
        }

        private final String sender;

        public String getSender() {
            return sender;
        }

        public NotificationTable(Notification notification, boolean isRequest) {
            this.type = isRequest ? "Abstention Request" : "Notification";

            this.title = notification.getTitle();
            this.notificationID = notification.getNotifID();

            DatabaseControl DBMS = new DatabaseControl();
            User senderUser = DBMS.getUser(notification.getSenderID());
            this.sender = senderUser.getSurname() + " " + senderUser.getName();
        }
    }

    @FXML
    private TableView<NotificationTable> NotificationTable;

    @FXML
    private TableColumn<NotificationTable, String> SenderColumn;

    @FXML
    private TableColumn<NotificationTable, String> TitleColumn;

    @FXML
    private TableColumn<NotificationTable, String> TypeNotificationColumn;

    @FXML
    private Button buttonHomePage, ButtonConfirm;

    @FXML
    void Home(ActionEvent event) throws IOException {
        AccountApplication n = new AccountApplication();
        DatabaseControl DBMS = new DatabaseControl();

        if (DBMS.getLoggedUserID() == 1) {
            n.changeScene("control/employer/HomepageEmployerModule.fxml");
        } else {
            n.changeScene("control/employee/HomepageEmployeeModule.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccountApplication n = new AccountApplication();
        DatabaseControl DBMS = new DatabaseControl();

        ArrayList<Notification> notificationList = DBMS.getNotificationList(DBMS.getLoggedUserID(), true);
        ArrayList<AbstentionRequest> abstentionRequestList = DBMS.getAbstentionRequest(DBMS.getLoggedUserID(), true);

        boolean isRequest = false;

        ArrayList<NotificationTable> list = new ArrayList<>();

        if (notificationList != null) {
            for (int i = 0; i < notificationList.size(); ++i) {

                if (abstentionRequestList != null) {
                    for (int j = 0; j < abstentionRequestList.size(); ++j) {
                        /* Add request only if the IDs match, and it was not accepted by the employer */
                        if ((notificationList.get(i).getNotifID() == abstentionRequestList.get(j).getNotifID())) {
                            if (!DBMS.isAccepted(i)) {
                                list.add(new NotificationTable(abstentionRequestList.get(j), true));
                            }
                            isRequest = true;
                            break;
                        }
                    }
                }

                if (!isRequest) {
                    list.add(new NotificationTable(notificationList.get(i), false));
                }

                isRequest = false;
            }
        }

        ObservableList<NotificationTable> notificationTable = FXCollections.observableArrayList(list);

        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        SenderColumn.setCellValueFactory(new PropertyValueFactory<>("Sender"));
        TypeNotificationColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));

        NotificationTable.setItems(notificationTable);
    }

    @FXML
    void ButtonConfirm(ActionEvent event) {
        DatabaseControl DBMS = new DatabaseControl();

        /* Retrieve an employee based on the corresponding user */
        DBMS.modifySelectedNotificationID(NotificationTable.getSelectionModel().getSelectedItem().getNotificationID());
        System.out.println(DBMS.getSelectedNotificationID());

        try {
            if (NotificationTable.getSelectionModel().getSelectedItem().getType().equals("Notification")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NotificationModule.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AbstentionNotificationModule.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
