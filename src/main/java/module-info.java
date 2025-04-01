module programa3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens programa3 to javafx.fxml;
    exports programa3;
}
