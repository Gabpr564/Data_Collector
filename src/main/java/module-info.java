module DataCollector {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.management;

    opens datacollector.datacollector to javafx.fxml;
    exports datacollector.datacollector;
}