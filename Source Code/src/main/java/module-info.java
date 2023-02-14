module com.example.azienda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires java.desktop;


    opens com.azienda to javafx.fxml;
    exports com.azienda;
    exports com.azienda.entity;
    opens com.azienda.entity to javafx.fxml;
    exports com.azienda.control;
    opens com.azienda.control to javafx.fxml;
    exports com.azienda.control.employee;
    opens com.azienda.control.employee to javafx.fxml;
    exports com.azienda.control.employer;
    opens com.azienda.control.employer to javafx.fxml;
    exports com.azienda.database;
    opens com.azienda.database to javafx.fxml;
    exports com.azienda.generic;
    opens com.azienda.generic to javafx.fxml;
    exports com.azienda.control.shared;
    opens com.azienda.control.shared to javafx.fxml;
    exports com.azienda.generic.presence;
    opens com.azienda.generic.presence to javafx.fxml;
}