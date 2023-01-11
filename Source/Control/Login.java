package Control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javafx.scene.control.Hyperlink;

import java.io.IOException;


public class Login {

    public Login() { }

    @FXML
    private Button button;
    @FXML
    private Label WrongLogin;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;
    @FXML
    private Hyperlink RetrivePassword;


    public void UserLogin(ActionEvent event) throws IOException {
        checkLogin();

    }

    private void checkLogin() throws IOException {

        HelloApplication n = new HelloApplication();

        if (Email.getText().isEmpty() && Password.getText().isEmpty()) {

             WrongLogin.setText("Please enter your data.");
             JOptionPane.showMessageDialog(null, "Please enter your data.");
         }

             Database Azienda = new Database("azienda", "postgres", "69");

             User user = Azienda.getUser(Email.getText(), Password.getText());
             System.out.println(user);

        if (user == null) {
            Email.clear();
            Password.clear();
            WrongLogin.setText("Wrong username or password!");
        }else {
            if (user.getID()==1){
                n.changeScene("homepageDatore.fxml");
            }else {
                n.changeScene("homepageDipendente.fxml");
            }

        }



      /*  if (Email.getText().equals("ciao") && Password.getText().equals("123")) {

            n.changeScene("homepageDatore.fxml");


        } else if (Email.getText().equals("andrea") && Password.getText().equals("raineri")) {


            n.changeScene("homepageDipendente.fxml");

        } else if (Email.getText().isEmpty() && Password.getText().isEmpty()) {

            WrongLogin.setText("Please enter your data.");
            JOptionPane.showMessageDialog(null, "Please enter your data.");
        } else {
            Email.clear();
            Password.clear();
            WrongLogin.setText("Wrong username or password!");
            JOptionPane.showMessageDialog(null, "Wrong username or password!");
        }*/
    }

    public void UserRetrive(ActionEvent event) throws IOException {

        Retrive();

    }

    private void Retrive() throws IOException {
        HelloApplication n = new HelloApplication();

        n.changeScene("RetriveCredentialsModule.fxml");
    }
}
