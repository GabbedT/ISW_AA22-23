module com.example.azienda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires java.desktop;


    opens com.example.azienda to javafx.fxml;
    exports com.example.azienda;
}