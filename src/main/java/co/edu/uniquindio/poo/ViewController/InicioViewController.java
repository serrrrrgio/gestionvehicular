package co.edu.uniquindio.poo.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import co.edu.uniquindio.poo.App;

public class InicioViewController {

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnVehiculos;

    @FXML
    private Button btnVolver;

    @FXML
    public void abrirAgregarCliente(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/Cliente.fxml", "Agregar Cliente", event,
                getClass());
    }

    @FXML
    public void abrirElegirVehiculo(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/Vehiculo.fxml", "Elegir Vehículo", event,
                getClass());
    }

    @FXML
    public void volver(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/Empresa.fxml", "Crear empresa", event,
                getClass());
    }

}
