package co.edu.uniquindio.poo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import co.edu.uniquindio.poo.Controller.MainController;
import co.edu.uniquindio.poo.Controller.MotoController;

public class App extends Application {

    private MainController mainController; // Atributo para el MainController

    @Override
    public void start(Stage primaryStage) {
        mainController = new MainController(); // Crear instancia de MainController

        try {
            // Cargar la vista Inicio.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/ViewController/Inicio.fxml"));
            Parent root = loader.load();

            // Aquí podrías pasar el mainController a Inicio.fxml si es necesario
            // Puedes obtener el controlador de la vista y pasarle el mainController
            // (en caso de que necesites acceder a él desde el controlador de Inicio)

            Scene scene = new Scene(root);
            primaryStage.setTitle("Gestión de Clientes y Vehículos");
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(event -> {
                // Manejo del cierre si es necesario
                System.out.println("Aplicación cerrada.");
            });
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
    }

    public void abrirAgregarCliente(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/ViewController/AgregarCliente.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Agregar Cliente");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
    }

    public void elegirVehiculo(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/ViewController/ElegirVehiculo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Elegir Vehículo");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
    }

    public void abrirMoto(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/ViewController/Moto.fxml"));
            Parent root = loader.load();
            MotoController controller = loader.getController();
            controller.setMainController(mainController); // Pasar la misma instancia de MainController

            stage.setTitle("Gestión de Motos");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
