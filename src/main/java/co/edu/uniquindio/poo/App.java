package co.edu.uniquindio.poo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import co.edu.uniquindio.poo.Model.Empresa;




public class App extends Application {

    private static Empresa empresa;

    @Override
    public void start(Stage primaryStage) {
        Empresa empresa = null;

        try {
            // Cargar la vista Inicio.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/ViewController/CrearEmpresa.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("CrearEmpresa");
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


    public static Empresa getEmpresa() {
        return empresa; 
    }

    public static void setEmpresa(Empresa empresaNueva) {
        empresa = empresaNueva;
    }

    public static void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void cambiarEscena(String fxml, String titulo, ActionEvent event, Class<?> clase){
        try {
            FXMLLoader loader = new FXMLLoader(clase.getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Obtener la ventana actual
            stage.setTitle(titulo); // Cambiar el título
            stage.centerOnScreen();
            stage.setScene(new Scene(root)); // Cambiar la escena
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
