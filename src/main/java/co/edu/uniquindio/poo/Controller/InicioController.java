package co.edu.uniquindio.poo.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioController {

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnVehiculos;

    @FXML
    private Button btnVolver;


    @FXML
    public void abrirAgregarCliente(ActionEvent event) {
        cambiarEscena("/co/edu/uniquindio/poo/ViewController/AgregarCliente.fxml", "Agregar Cliente");
    }

    @FXML
    public void abrirElegirVehiculo(ActionEvent event) {
        cambiarEscena("/co/edu/uniquindio/poo/ViewController/ElegirVehiculo.fxml", "Elegir Vehículo");
    }

    @FXML
    public void volver(ActionEvent event) {
        cambiarEscena("/co/edu/uniquindio/poo/ViewController/CrearEmpresa.fxml", "Crear empresa");
    }

    private void cambiarEscena(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) btnClientes.getScene().getWindow(); // Obtén la ventana actual
            stage.setTitle(titulo); // Cambia el título
            stage.setScene(new Scene(root)); // Establece la nueva escena
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

