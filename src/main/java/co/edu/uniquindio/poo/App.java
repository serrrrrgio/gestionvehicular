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
            // Cargar la vista Inicio.fxml desde la carpeta ViewController
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/ViewController/Inicio.fxml"));
            Parent root = loader.load();

            // Crear la escena con el archivo FXML cargado
            Scene scene = new Scene(root);

            // Configurar el escenario principal
            primaryStage.setTitle("Gestión de Clientes y Vehículos");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

