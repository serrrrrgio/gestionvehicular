package co.edu.uniquindio.poo.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Model.Moto;

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
    }

    @FXML
    private void abrirMoto(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/ViewController/Moto.fxml", "Moto", event, getClass());
    }

    @FXML
    private void abrirAuto(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/ViewController/Auto.fxml", "Auto", event, getClass());
    }

    @FXML
    private void abrirCamion(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/ViewController/Camioneta.fxml", "Camioneta", event, getClass());
    }

    @FXML
    private void salir(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/ViewController/Inicio.fxml", "Inicio", event, getClass());
    }
}
