module com.example.messenger_nure {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires commons.codec;


    opens ua.com.alevel.messenger_nure to javafx.fxml;
    exports ua.com.alevel.messenger_nure;
    exports ua.com.alevel.messenger_nure.view.controller;
    opens ua.com.alevel.messenger_nure.view.controller to javafx.fxml;
}