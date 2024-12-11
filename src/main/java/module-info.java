module com.example.demo {
    requires javafx.controls;
    // requires javafx.fxml;
    requires javafx.media;
    // Required for LoggerUtil
    requires java.logging;
    // Required for PropertyChange Observer pattern
    requires java.desktop;

    exports com.example.demo;
}