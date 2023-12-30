module co.edu.uniquindio.tienda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens co.edu.uniquindio.tienda to javafx.fxml;
    exports co.edu.uniquindio.tienda;
    exports co.edu.uniquindio.tienda.controlador;
    opens co.edu.uniquindio.tienda.controlador to javafx.fxml;
    opens co.edu.uniquindio.tienda.modelo to javafx.base;


}