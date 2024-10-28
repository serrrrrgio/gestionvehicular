package co.edu.uniquindio.poo.Controller;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Model.Moto;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearEmpresaController {
    @FXML
    private Button btnCrearEmpresa;

    @FXML
    private TextField txtNombre;

    @FXML
    public void initialize() {
        
    }

    @FXML
    public void crearEmpresa() {
        if(!txtNombre.getText().isBlank()){
            
        }
    }

    public void cambiarEscena(String fxml, String titulo){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) btnCrearEmpresa.getScene().getWindow(); // Obtener la ventana actual
            stage.setTitle(titulo); // Cambiar el título
            stage.setScene(new Scene(root)); // Cambiar la escena
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}