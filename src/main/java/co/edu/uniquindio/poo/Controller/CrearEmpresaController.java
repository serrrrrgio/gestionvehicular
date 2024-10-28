package co.edu.uniquindio.poo.Controller;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Model.Empresa;
import co.edu.uniquindio.poo.Model.Moto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private Button btnSalir;

    @FXML
    private TextField txtNombre;

    @FXML
    public void initialize() {
        
    }

    @FXML
    public void crearEmpresa() {
        String nombre = txtNombre.getText();
        if(nombre.isBlank()){
            App.mostrarAlerta("Campos vacíos", "Por favor ingrese el nombre de su empresa");
            return;
        } 

        if(App.getMainController().getEmpresa() == null){
            App.getMainController().setEmpresa(new Empresa(nombre));
        }

        else{
            App.getMainController().getEmpresa().setNombre(nombre);
        }

        cambiarEscena("/co/edu/uniquindio/poo/ViewController/Inicio.fxml", "Inicio");
    }

    @FXML
    public void salirAplicacion(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close(); // Cierra la aplicación
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
