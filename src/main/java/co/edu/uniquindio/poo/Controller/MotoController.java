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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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

    private ObservableList<Moto> motos; // La lista de motos
    private MainController mainController; // Referencia al MainController

    @FXML
    public void initialize() {
        // Inicializar columnas de la tabla
        tbcMatricula.setCellValueFactory(cellData -> cellData.getValue().numeroMatriculaProperty());
        tbcMarca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        tbcModelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
        tbcFechaFabricacion.setCellValueFactory(cellData -> cellData.getValue().fechaFabricacionProperty());
        tbcTarifaBase.setCellValueFactory(cellData -> cellData.getValue().tarifaBaseProperty().asObject());
        tbcTipoCaja.setCellValueFactory(cellData -> cellData.getValue().tipoCajaProperty());

        // Llenar ChoiceBox con los valores del Enum TipoCaja
        choiceTipoCaja.setItems(FXCollections.observableArrayList(TipoCaja.values()));

        // Agregar un listener para la selección de una moto en la tabla
        tblListMoto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarDatosMoto(newValue);
            }
        });
    }

    // Método para establecer el MainController
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        this.motos = mainController.getMotos(); // Inicializar la lista aquí
        inicializarTabla(); // Inicializa la tabla después de establecer el MainController
    }

    // Método para establecer la lista de motos
    public void setMotos(ObservableList<Moto> motos) {
        this.motos = motos;
        inicializarTabla(); // Actualiza la tabla con la nueva lista
    }

    // Método para inicializar la tabla
    private void inicializarTabla() {
        if (motos == null) {
            motos = FXCollections.observableArrayList(); // Asegurarse de que la lista no sea null
        }
        // Establecer la tabla para mostrar la lista de motos
        tblListMoto.setItems(motos);
        System.out.println("Tabla inicializada con " + motos.size() + " motos."); // Debug
    }

    // Método para cargar los datos de la moto seleccionada en los campos de texto
    private void cargarDatosMoto(Moto moto) {
        txtMatricula.setText(moto.getNumeroMatricula());
        txtMarca.setText(moto.getMarca());
        txtModelo.setText(moto.getModelo());
        datePickerFechaFabricacion.setValue(moto.getFechaFabricacion());
        txtTarifaBase.setText(String.valueOf(moto.getTarifaBase()));
        choiceTipoCaja.setValue(moto.getTipoCaja());
    }

    // Método para agregar una nueva moto
    @FXML
    public void agregarMoto(ActionEvent event) {
        String matricula = txtMatricula.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        LocalDate fechaFabricacion = datePickerFechaFabricacion.getValue();
        String tarifaBaseStr = txtTarifaBase.getText();
        TipoCaja tipoCaja = choiceTipoCaja.getValue();

        // Validar la tarifa base
        if (tarifaBaseStr.isEmpty()) {
            mostrarAlerta("Campo Tarifa Base Vacío", "Por favor, ingresa una tarifa base válida.");
            return;
        }

        double tarifaBase;
        try {
            tarifaBase = Double.parseDouble(tarifaBaseStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Formato de Tarifa Base Inválido", "Por favor, ingresa un número válido para la tarifa base.");
            return;
        }

        // Crear nueva moto y agregarla a la lista
        Moto nuevaMoto = new Moto(matricula, marca, modelo, fechaFabricacion, tarifaBase, tipoCaja);
        if (motos == null) {
            motos = FXCollections.observableArrayList(); // Asegurarse de que la lista no sea null
        }
        motos.add(nuevaMoto); // Agregar la moto a la lista de motos

        // Actualiza la tabla
        tblListMoto.setItems(motos); // Actualiza la tabla con la lista de motos

        // Limpiar campos después de agregar
        limpiarCampos();

        System.out.println("Moto agregada: " + nuevaMoto); // Debug
        System.out.println("Total motos: " + motos.size()); // Debug
    }

    // Método para eliminar una moto seleccionada
    @FXML
    public void eliminarMoto(ActionEvent event) {
        Moto selectedMoto = tblListMoto.getSelectionModel().getSelectedItem();
        if (selectedMoto != null) {
            motos.remove(selectedMoto); // Remover de la lista
            limpiarCampos(); // Limpiar los campos al eliminar
            System.out.println("Moto eliminada: " + selectedMoto); // Debug
            System.out.println("Total motos: " + motos.size()); // Debug
        } else {
            mostrarAlerta("No hay moto seleccionada", "Por favor, selecciona una moto para eliminar.");
        }
    }

    // Método para actualizar los datos de una moto seleccionada
    @FXML
    public void actualizarMoto(ActionEvent event) {
        Moto selectedMoto = tblListMoto.getSelectionModel().getSelectedItem();
        if (selectedMoto != null) {
            selectedMoto.setNumeroMatricula(txtMatricula.getText());
            selectedMoto.setMarca(txtMarca.getText());
            selectedMoto.setModelo(txtModelo.getText());
            selectedMoto.setFechaFabricacion(datePickerFechaFabricacion.getValue());

            String tarifaBaseStr = txtTarifaBase.getText();
            if (tarifaBaseStr.isEmpty()) {
                mostrarAlerta("Campo Tarifa Base Vacío", "Por favor, ingresa una tarifa base válida.");
                return;
            }

            double tarifaBase;
            try {
                tarifaBase = Double.parseDouble(tarifaBaseStr);
                selectedMoto.setTarifaBase(tarifaBase);
            } catch (NumberFormatException e) {
                mostrarAlerta("Formato de Tarifa Base Inválido", "Por favor, ingresa un número válido para la tarifa base.");
                return;
            }

            selectedMoto.setTipoCaja(choiceTipoCaja.getValue());
            tblListMoto.refresh(); // Refrescar la tabla para mostrar los cambios
            limpiarCampos(); // Limpiar los campos después de actualizar
            
            System.out.println("Moto actualizada: " + selectedMoto); // Debug
        } else {
            mostrarAlerta("No hay moto seleccionada", "Por favor, selecciona una moto para actualizar.");
        }
    }

    // Método para regresar a la escena anterior
    @FXML
    public void regresar(ActionEvent event) {
        cambiarEscena("/co/edu/uniquindio/poo/ViewController/ElegirVehiculo.fxml", "Elegir Vehículo");
    }

    // Método auxiliar para limpiar los campos de entrada
    private void limpiarCampos() {
        txtMatricula.clear();
        txtMarca.clear();
        txtModelo.clear();
        datePickerFechaFabricacion.setValue(null);
        txtTarifaBase.clear();
        choiceTipoCaja.setValue(null);
        tblListMoto.getSelectionModel().clearSelection(); // Deseleccionar la moto en la tabla
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para cambiar de escena
    private void cambiarEscena(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) btnRegresar.getScene().getWindow(); // Obtener la ventana actual
            stage.setTitle(titulo); // Cambiar el título
            stage.setScene(new Scene(root)); // Cambiar la escena
            
            // Si se regresa a la escena ElegirVehiculo, establecer la lista de motos
            if (fxml.equals("/co/edu/uniquindio/poo/ViewController/ElegirVehiculo.fxml")) {
                ElegirVehiculoController elegirVehiculoController = loader.getController();
                elegirVehiculoController.setMotos(motos); // Pasar la lista de motos, si es necesario
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
