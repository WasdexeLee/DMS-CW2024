module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    // Required for LoggerUtil
    requires java.logging;

    // Required for PropertyChange Observer pattern
    requires java.desktop;


    opens com.example.demo to javafx.fxml;
    // exports com.example.demo.controller;
    exports com.example.demo.controller;
}