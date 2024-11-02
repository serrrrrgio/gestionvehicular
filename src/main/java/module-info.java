module co.edu.uniquindio.poo {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.Controller to javafx.fxml;
    opens co.edu.uniquindio.poo.Model to javafx.base;

    exports co.edu.uniquindio.poo;
}
