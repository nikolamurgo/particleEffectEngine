module example.particleengine {
    requires javafx.controls;
    requires javafx.fxml;


    opens example.particleengine to javafx.fxml;
    exports example.particleengine;
}