module avneet.lab5tipcalculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens avneet.lab5tipcalculator to javafx.fxml;
    exports avneet.lab5tipcalculator;
}