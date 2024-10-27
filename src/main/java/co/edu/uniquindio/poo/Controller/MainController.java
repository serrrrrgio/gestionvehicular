package co.edu.uniquindio.poo.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import co.edu.uniquindio.poo.Model.Cliente;

public class MainController {

    private ObservableList<Cliente> clientes; // Lista de clientes

    public MainController() {
        // Inicializa la lista de clientes
        this.clientes = FXCollections.observableArrayList(); // Aquí inicializamos la lista
    }

    public ObservableList<Cliente> getClientes() {
        return clientes; // Método para obtener la lista de clientes
    }

    public void cambiarEscena(String fxml, String titulo, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            // Si estamos abriendo AgregarCliente.fxml, pasamos la lista de clientes
            if (fxml.equals("/co/edu/uniquindio/poo/ViewController/AgregarCliente.fxml")) {
                AgregarClienteController controller = loader.getController();
                controller.setClientes(clientes); // Pasar la lista de clientes
            }

            stage.setTitle(titulo);
            stage.setScene(new Scene(root)); // Establece la nueva escena
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error si ocurre una excepción
        }
    }
}

/*
Nota de Sergio:
Creamos la clase MainController.java para hacer que manejar las diferentes
pantallas de la app sea más fácil. Esta clase guarda una sola lista de clientes,
así que podemos compartir info entre los controladores sin problemas. Al usar
MainController, mantenemos la parte de navegar por la app separada de lo que 
hace cada controlador. Esto hace que el código sea más fácil de entender y mantener.
*/
