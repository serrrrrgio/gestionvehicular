package co.edu.uniquindio.poo.Controller;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Model.Empresa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public void crearEmpresa(ActionEvent event) {
        String nombre = txtNombre.getText();
        if(nombre.isBlank()){
            App.mostrarAlerta("Campos vacíos", "Por favor ingrese el nombre de su empresa");
            return;
        } 

        if(App.getEmpresa() == null){
            App.setEmpresa(new Empresa(nombre));
        }

        else{
            App.getEmpresa().setNombre(nombre);
        }

        App.cambiarEscena("/co/edu/uniquindio/poo/ViewController/Inicio.fxml", "Inicio", event, getClass());
    }

    @FXML
    public void salirAplicacion(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close(); // Cierra la aplicación
    }

}
