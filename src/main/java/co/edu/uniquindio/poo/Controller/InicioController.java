package co.edu.uniquindio.poo.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
import co.edu.uniquindio.poo.Model.Cliente;

public class InicioController {

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnVehiculos;

    @FXML
    private Button btnSalir;

    private ObservableList<Cliente> clientes; // Lista de clientes

    // Este método se llamará al inicio de la aplicación para establecer la lista de clientes
    public void setClientes(ObservableList<Cliente> clientes) {
        this.clientes = clientes; // Asigna la lista de clientes al controlador
    }

    @FXML
    public void abrirAgregarCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/ViewController/AgregarCliente.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de AgregarCliente
            AgregarClienteController agregarClienteController = loader.getController();
            agregarClienteController.setClientes(clientes); // Pasar la lista de clientes

            Stage stage = (Stage) btnClientes.getScene().getWindow(); // Obtén la ventana actual
            stage.setScene(new Scene(root)); // Establece la nueva escena
            stage.setTitle("Agregar Cliente"); // Cambia el título
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void abrirElegirVehiculo(ActionEvent event) {
        cambiarEscena("/co/edu/uniquindio/poo/ViewController/ElegirVehiculo.fxml", "Elegir Vehículo");
    }

    @FXML
    public void salirAplicacion(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close(); // Cierra la aplicación
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

