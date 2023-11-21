module com.company {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.company to javafx.fxml;
    exports com.company;
}