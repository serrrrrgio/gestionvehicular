package co.edu.uniquindio.poo.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class ElegirVehiculoController {

    @FXML
    private Button btnMoto;

    @FXML
    private Button btnAuto;

    @FXML
    private Button btnCamion;

    @FXML
    private Button btnSalir; // Botón para salir y volver a la escena de inicio

    @FXML
    public void initialize() {
        // Inicialización adicional si es necesario
    }

    @FXML
    private void abrirMoto(ActionEvent event) {
        cambiarEscena("/co/edu/uniquindio/poo/ViewController/Moto.fxml", "Moto");
    }

    @FXML
    private void abrirAuto(ActionEvent event) {
        cambiarEscena("/co/edu/uniquindio/poo/ViewController/Auto.fxml", "Auto");
    }

    @FXML
    private void abrirCamion(ActionEvent event) {
        cambiarEscena("/co/edu/uniquindio/poo/ViewController/Camioneta.fxml", "Camioneta");
    }

    @FXML
    private void salir(ActionEvent event) {
        cambiarEscena("/co/edu/uniquindio/poo/ViewController/Inicio.fxml", "Inicio");
    }

    private void cambiarEscena(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) btnMoto.getScene().getWindow(); // Obtener la ventana actual
            stage.setTitle(titulo);
            stage.setScene(new Scene(root)); // Cambiar la escena
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
