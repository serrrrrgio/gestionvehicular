package co.edu.uniquindio.poo.ViewController;

import java.time.LocalDate;

import co.edu.uniquindio.poo.Model.TipoCaja;
import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Controller.MotoController;
import co.edu.uniquindio.poo.Model.Moto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MotoViewController {

    @FXML
    private TableView<Moto> tblListMoto;
    @FXML
    private TableColumn<Moto, String> tbcMatricula;
    @FXML
    private TableColumn<Moto, String> tbcMarca;
    @FXML
    private TableColumn<Moto, String> tbcModelo;
    @FXML
    private TableColumn<Moto, LocalDate> tbcFechaFabricacion;
    @FXML
    private TableColumn<Moto, Double> tbcTarifaBase;
    @FXML
    private TableColumn<Moto, TipoCaja> tbcTipoCaja;

    @FXML
    private TextField txtMatricula;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private DatePicker datePickerFechaFabricacion;
    @FXML
    private TextField txtTarifaBase;
    @FXML
    private ChoiceBox<TipoCaja> choiceTipoCaja;

    @FXML
    private Button btnAgregarMoto;
    @FXML
    private Button btnEliminarMoto;
    @FXML
    private Button btnActualizarMoto;
    @FXML
    private Button btnRegresar;

    Moto motoSeleccionada;

    private MotoController motoController;

    @FXML
    public void initialize() {
        motoController = new MotoController(App.getEmpresa());
        setMotos();

        inicializarData();

        agregarListener();
    }

    public void agregarListener() {
        // Agregar un listener para la selección de una moto en la tabla
        tblListMoto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            motoSeleccionada = newValue;
            mostrarInformacionMoto(newValue);
        });
    }

    public void inicializarData() {
        tbcMatricula.setCellValueFactory(cellData -> cellData.getValue().numeroMatriculaProperty());
        tbcMarca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        tbcModelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
        tbcFechaFabricacion.setCellValueFactory(cellData -> cellData.getValue().fechaFabricacionProperty());
        tbcTarifaBase.setCellValueFactory(cellData -> cellData.getValue().tarifaBaseProperty().asObject());
        tbcTipoCaja.setCellValueFactory(cellData -> cellData.getValue().tipoCajaProperty());
        // Llenar ChoiceBox con los valores del Enum TipoCaja
        choiceTipoCaja.setItems(FXCollections.observableArrayList(TipoCaja.values()));
    }

    // Método para cargar los datos de la moto seleccionada en los campos de texto
    private void mostrarInformacionMoto(Moto moto) {
        if (moto != null) {
            txtMatricula.setText(moto.getNumeroMatricula());
            txtMarca.setText(moto.getMarca());
            txtModelo.setText(moto.getModelo());
            datePickerFechaFabricacion.setValue(moto.getFechaFabricacion());
            txtTarifaBase.setText(String.valueOf(moto.getTarifaBase()));
            choiceTipoCaja.setValue(moto.getTipoCaja());
        } else {
            limpiarCampos();
        }

    }

    // Método para establecer la lista de motos
    public void setMotos() {
        tblListMoto.setItems(motoController.obtenerMotos());
    }

    // Método para verificar si los campos están vacíos
    public boolean camposVacios(String matricula, String marca, String modelo, LocalDate fechaFabricacion,
            String tarifa,
            TipoCaja tipoCaja) {
        return matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty() || fechaFabricacion == null
                || tarifa.isEmpty() || tipoCaja == null;
    }

    // Método para verificar que todos los campos tengan un formato válido
    public boolean camposValidos(String matricula, String marca, String modelo, LocalDate fechaFabricacion,
            String tarifaBaseCadena,
            TipoCaja tipoCaja) {
        if (camposVacios(matricula, marca, modelo, fechaFabricacion, tarifaBaseCadena, tipoCaja)) {
            App.mostrarAlerta("Campos vacíos", "Por favor llene todos los campos");
            return false;
        }

        double tarifaBase;
        try {
            tarifaBase = Double.parseDouble(tarifaBaseCadena);
        } catch (NumberFormatException e) {
            App.mostrarAlerta("Formato de Tarifa Base Inválido",
                    "Por favor, ingresa un número válido para la tarifa base.");
            return false;
        }

        if (tarifaBase <= 0) {
            App.mostrarAlerta("Tarifa base inválida", "Por favor ingresar una tarifa mayor que 0");
            return false;
        }

        return true;
    }

    // Método para agregar una nueva moto
    @FXML
    public void agregarMoto(ActionEvent event) {
        String matricula = txtMatricula.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        LocalDate fechaFabricacion = datePickerFechaFabricacion.getValue();
        String tarifaBaseCadena = txtTarifaBase.getText();
        TipoCaja tipoCaja = choiceTipoCaja.getValue();

        if (camposValidos(matricula, marca, modelo, fechaFabricacion, tarifaBaseCadena, tipoCaja)) {
            Moto moto = crearMoto();

            if (motoController.agregarVehiculo(moto)) {
                // Actualiza la tabla
                setMotos();

                // Limpiar campos después de agregar
                limpiarCampos();
            } else {
                App.mostrarAlerta("Error", "Ya existe una moto con el número de matrícula " + matricula);
            }

        }
    }

    public Moto crearMoto() {
        return new Moto(txtMatricula.getText(), txtMarca.getText(), txtModelo.getText(),
                datePickerFechaFabricacion.getValue(), null, Double.parseDouble(txtTarifaBase.getText()),
                choiceTipoCaja.getValue());
    }

    // Método para eliminar una moto seleccionada
    @FXML
    public void eliminarMoto(ActionEvent event) {
        Moto motoSeleccionada = tblListMoto.getSelectionModel().getSelectedItem();
        if (motoSeleccionada == null) {
            App.mostrarAlerta("No hay moto seleccionada", "Por favor, selecciona una moto para eliminar.");
            return;
        }

        // Se remueve la moto de la lista
        motoController.eliminarVehiculo(motoSeleccionada);

        // Se limpian los campos
        limpiarCampos();
    }

    // Método para actualizar los datos de una moto seleccionada
    @FXML
    public void actualizarMoto(ActionEvent event) {
        if (motoSeleccionada == null) {
            App.mostrarAlerta("No hay moto seleccionada", "Por favor, selecciona una moto para actualizar.");
            return;
        }

        String matricula = txtMatricula.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        LocalDate fechaFabricacion = datePickerFechaFabricacion.getValue();
        String tarifaBaseCadena = txtTarifaBase.getText();
        TipoCaja tipoCaja = choiceTipoCaja.getValue();

        if (camposValidos(matricula, marca, modelo, fechaFabricacion, tarifaBaseCadena, tipoCaja)) {
            if (motoController.actualizarMoto(motoSeleccionada, matricula, marca, modelo, fechaFabricacion,
                    Double.parseDouble(tarifaBaseCadena),
                    tipoCaja)) {
                // Refrescar la tabla para mostrar los cambios
                tblListMoto.refresh();

                // Limpiar los campos después de actualizar
                limpiarCampos();
                limpiarSeleccion();
            } else {
                App.mostrarAlerta("Error", "Ya existe una moto con el número de matrícula " + matricula);
            }
        }

    }

    // Método para regresar a la escena anterior
    @FXML
    public void regresar(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/Vehiculo.fxml", "Elegir Vehículo", event,
                getClass());
    }

    // Método auxiliar para limpiar los campos de entrada
    private void limpiarCampos() {
        txtMatricula.clear();
        txtMarca.clear();
        txtModelo.clear();
        datePickerFechaFabricacion.setValue(null);
        txtTarifaBase.clear();
        choiceTipoCaja.setValue(null);

        // Deseleccionar la moto en la tabla
        tblListMoto.getSelectionModel().clearSelection();
    }

    private void limpiarSeleccion() {
        tblListMoto.getSelectionModel().clearSelection(); // Deseleccionar el cliente en la tabla
        motoSeleccionada = null; // Reiniciar la referencia al cliente seleccionado
    }
}
