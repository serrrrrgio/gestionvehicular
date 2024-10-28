package co.edu.uniquindio.poo.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Model.Moto;
import javafx.scene.control.Alert;

public class ElegirVehiculoController {

    @FXML
    private Button btnMoto;

    @FXML
    private Button btnAuto;

    @FXML
    private Button btnCamion;

    @FXML
    private Button btnSalir; // Botón para salir y volver a la escena de inicio

    ObservableList<Moto> motos;

    @FXML
    public void initialize() {
        motos = App.getMainController().getMotos();
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

            // Si estamos abriendo la vista de Moto, pasamos la lista de motos
            if (fxml.equals("/co/edu/uniquindio/poo/ViewController/Moto.fxml")) {
                if (motos.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Advertencia");
                    alert.setHeaderText("Lista de Motos Vacía");
                    alert.setContentText("No hay motos disponibles para mostrar.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            // Mejor manejo de excepciones
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrió un error al cargar la escena");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            e.printStackTrace(); // Imprimir la traza del error en la consola para depuración
        }
    }
}
