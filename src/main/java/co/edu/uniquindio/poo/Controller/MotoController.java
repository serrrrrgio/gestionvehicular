package co.edu.uniquindio.poo.Controller;

import java.time.LocalDate;
import co.edu.uniquindio.poo.Model.TipoCaja;
import co.edu.uniquindio.poo.Model.Moto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MotoController {

    @FXML private TableView<Moto> tblListMoto;
    @FXML private TableColumn<Moto, String> tbcMatricula;
    @FXML private TableColumn<Moto, String> tbcMarca;
    @FXML private TableColumn<Moto, String> tbcModelo;
    @FXML private TableColumn<Moto, LocalDate> tbcFechaFabricacion;
    @FXML private TableColumn<Moto, Double> tbcTarifaBase;
    @FXML private TableColumn<Moto, TipoCaja> tbcTipoCaja;

    @FXML private TextField txtMatricula;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private DatePicker datePickerFechaFabricacion;
    @FXML private TextField txtTarifaBase;
    @FXML private ChoiceBox<TipoCaja> choiceTipoCaja;

    @FXML private Button btnAgregarMoto;
    @FXML private Button btnEliminarMoto;
    @FXML private Button btnActualizarMoto;
    @FXML private Button btnRegresar;

    private ObservableList<Moto> motos;
    private MainController mainController;

    // Método que se llama al inicializar el controlador
    @FXML
    public void initialize() {
        // Inicializar columnas de la tabla
        tbcMatricula.setCellValueFactory(cellData -> cellData.getValue().matriculaProperty());
        tbcMarca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        tbcModelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
        tbcFechaFabricacion.setCellValueFactory(cellData -> cellData.getValue().fechaFabricacionProperty());
        tbcTarifaBase.setCellValueFactory(cellData -> cellData.getValue().tarifaBaseProperty().asObject());
        tbcTipoCaja.setCellValueFactory(cellData -> cellData.getValue().tipoCajaProperty());

        // Llenar ChoiceBox con los valores del Enum TipoCaja
        choiceTipoCaja.setItems(FXCollections.observableArrayList(TipoCaja.values()));
    }

    // Método para establecer el MainController
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        this.motos = mainController.getMotos(); // Inicializar la lista aquí
        inicializarTabla(); // Inicializa la tabla después de establecer el MainController
    }

    // Método para inicializar la tabla
    private void inicializarTabla() {
        if (motos == null) {
            System.err.println("Error: La lista de motos no ha sido inicializada.");
            return;
        }
        tblListMoto.setItems(motos); // Establecer la lista en la tabla
    }

    // Método para agregar una nueva moto
    @FXML
    public void agregarMoto(ActionEvent event) {
        if (motos == null) {
            System.err.println("Error: La lista de motos no ha sido inicializada.");
            return; // Salir del método si motos es nulo
        }

        String matricula = txtMatricula.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        LocalDate fechaFabricacion = datePickerFechaFabricacion.getValue();
        double tarifaBase = Double.parseDouble(txtTarifaBase.getText());
        TipoCaja tipoCaja = choiceTipoCaja.getValue();

        // Crear nueva moto y agregarla a la lista
        Moto nuevaMoto = new Moto(matricula, marca, modelo, fechaFabricacion, tarifaBase, tipoCaja);
        motos.add(nuevaMoto);

        // Limpiar campos después de agregar
        limpiarCampos();
    }

    // Método para eliminar una moto seleccionada
    @FXML
    public void eliminarMoto(ActionEvent event) {
        Moto selectedMoto = tblListMoto.getSelectionModel().getSelectedItem();
        if (selectedMoto != null) {
            motos.remove(selectedMoto);
        }
    }

    // Método para actualizar los datos de una moto seleccionada
    @FXML
    public void actualizarMoto(ActionEvent event) {
        Moto selectedMoto = tblListMoto.getSelectionModel().getSelectedItem();
        if (selectedMoto != null) {
            selectedMoto.setMatricula(txtMatricula.getText());
            selectedMoto.setMarca(txtMarca.getText());
            selectedMoto.setModelo(txtModelo.getText());
            selectedMoto.setFechaFabricacion(datePickerFechaFabricacion.getValue());
            selectedMoto.setTarifaBase(Double.parseDouble(txtTarifaBase.getText()));
            selectedMoto.setTipoCaja(choiceTipoCaja.getValue());
            tblListMoto.refresh();
        }
    }

    // Método para regresar a la escena anterior
    @FXML
    public void regresar(ActionEvent event) {
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        mainController.cambiarEscena("/co/edu/uniquindio/poo/ViewController/ElegirVehiculo.fxml", "Elegir Vehículo", stage);
    }

    // Método auxiliar para limpiar los campos de entrada
    private void limpiarCampos() {
        txtMatricula.clear();
        txtMarca.clear();
        txtModelo.clear();
        datePickerFechaFabricacion.setValue(null);
        txtTarifaBase.clear();
        choiceTipoCaja.setValue(null);
    }
}
