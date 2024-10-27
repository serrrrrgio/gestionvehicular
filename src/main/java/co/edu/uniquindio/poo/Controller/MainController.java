package co.edu.uniquindio.poo.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import co.edu.uniquindio.poo.Model.Cliente;
import co.edu.uniquindio.poo.Model.Moto;

public class MainController {

    private ObservableList<Cliente> clientes; // Lista de clientes
    private ObservableList<Moto> motos; // Lista de motos

    public MainController() {
        // Inicializa las listas
        this.clientes = FXCollections.observableArrayList();
        this.motos = FXCollections.observableArrayList(); // Inicializa la lista de motos
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }

    public ObservableList<Moto> getMotos() { // Método para obtener la lista de motos
        return motos;
    }

    public void cambiarEscena(String fxml, String titulo, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            // Si estamos abriendo Moto.fxml, pasamos la lista de motos
            if (fxml.equals("/co/edu/uniquindio/poo/ViewController/Moto.fxml")) {
                MotoController controller = loader.getController();
                if (controller != null) {
                    controller.setMainController(this); // Pasar la instancia de MainController
                    controller.setMotos(motos); // Pasar la lista de motos
                } else {
                    System.err.println("Error: No se pudo obtener el controlador de Moto.fxml.");
                }
            }

            // Establecer el título y la escena
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show(); // Mostrar la nueva escena
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
    }
}
