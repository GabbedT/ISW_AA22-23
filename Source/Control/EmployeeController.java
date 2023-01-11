package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.io.IOException;


public class EmployeeController {

    @FXML
    private TextField DataFrom;

    @FXML
    private TextField DataTo;

    @FXML
    private MenuButton MenuType;

    @FXML
    private TextField Reason;

    @FXML
    private Button buttonConfirmChangePassword;

    @FXML
    private Button buttonHomePage1;

    @FXML
    void ButtonConfirm(ActionEvent event) {

    }

    @FXML
    void userHomePage(ActionEvent event)throws IOException {
        HomePage();
    }



    public void MenuType(ActionEvent event) throws IOException {
        Menu();
    }


    private void Send() throws IOException{

    }

    private void HomePage() throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("homepageDipendente.fxml");

    }

    private void Menu() throws IOException{

    }



}
