package co.edu.uniquindio.poo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar la vista Inicio.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/ViewController/Inicio.fxml"));
            Parent root = loader.load();
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

    public static void main(String[] args) {
        launch(args);
    }
}
