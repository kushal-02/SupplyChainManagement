module com.example.supplychainmanegment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supplychainmanegment to javafx.fxml;
    exports com.example.supplychainmanegment;
}