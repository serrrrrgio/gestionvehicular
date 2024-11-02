package co.edu.uniquindio.poo.ViewController;

import java.time.LocalDate;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Controller.AutoController;
import co.edu.uniquindio.poo.Controller.CamionetaController;
import co.edu.uniquindio.poo.Model.Auto;
import co.edu.uniquindio.poo.Model.Empresa;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AutoViewController {

    @FXML
    private TableView<Auto> tblListAuto;
    @FXML
    private TableColumn<Auto, String> tbcMatricula;
    @FXML
    private TableColumn<Auto, String> tbcMarca;
    @FXML
    private TableColumn<Auto, String> tbcModelo;
    @FXML
    private TableColumn<Auto, LocalDate> tbcFechaFabricacion;
    @FXML
    private TableColumn<Auto, Double> tbcTarifaBase;
    @FXML
    private TableColumn<Auto, Integer> tbcNumeroPuertas;

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
    private TextField txtNumeroPuertas;

    @FXML
    private Button btnAgregarAuto;
    @FXML
    private Button btnEliminarAuto;
    @FXML
    private Button btnActualizarAuto;
    @FXML
    private Button btnRegresar;

    private Auto autoSeleccionado;

    private AutoController autoController;

    @FXML
    public void initialize() {
        autoController = new AutoController(App.getEmpresa());

        setAutos();
        // Inicializar columnas de la tabla

        inicializarData();

        mostrarInformacionAuto(autoSeleccionado);

    }

    public void agregarListener() {
        // Agregar un listener para la selección de una auto en la tabla
        tblListAuto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            autoSeleccionado = newValue;

        });
    }

    public void inicializarData() {
        tbcMatricula.setCellValueFactory(cellData -> cellData.getValue().numeroMatriculaProperty());
        tbcMarca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        tbcModelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
        tbcFechaFabricacion.setCellValueFactory(cellData -> cellData.getValue().fechaFabricacionProperty());
        tbcTarifaBase.setCellValueFactory(cellData -> cellData.getValue().tarifaBaseProperty().asObject());
        tbcNumeroPuertas.setCellValueFactory(cellData -> cellData.getValue().numeroPuertasProperty().asObject());
    }

    // Método para cargar los datos de la auto seleccionada en los campos de texto
    private void mostrarInformacionAuto(Auto auto) {
        if (auto != null) {
            txtMatricula.setText(auto.getNumeroMatricula());
            txtMarca.setText(auto.getMarca());
            txtModelo.setText(auto.getModelo());
            datePickerFechaFabricacion.setValue(auto.getFechaFabricacion());
            txtTarifaBase.setText(String.valueOf(auto.getTarifaBase()));
            txtNumeroPuertas.setText(String.valueOf(auto.getNumeroPuertas()));
        } else {
            limpiarCampos();
        }

    }

    // Método para establecer la lista de autos
    public void setAutos() {
        tblListAuto.setItems(autoController.obtenerAutos());
    }

    // Método para verificar si los campos están vacíos
    boolean camposVacios(String matricula, String marca, String modelo, LocalDate fechaFabricacion, String tarifa,
            String numeroPuertas) {
        return matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty() || fechaFabricacion == null
                || tarifa.isEmpty() || numeroPuertas.isEmpty();
    }

    // Método para agregar una nueva auto
    @FXML
    public void agregarAuto(ActionEvent event) {
        String matricula = txtMatricula.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        LocalDate fechaFabricacion = datePickerFechaFabricacion.getValue();
        String tarifaBaseCadena = txtTarifaBase.getText();
        String numeroPuertasCadena = txtNumeroPuertas.getText();

        if (camposVacios(matricula, marca, modelo, fechaFabricacion, tarifaBaseCadena, numeroPuertasCadena)) {
            App.mostrarAlerta("Campos vacíos", "Por favor llene todos los campos");
            return;
        }

        try {
            double tarifaBase = Double.parseDouble(tarifaBaseCadena);
        } catch (NumberFormatException e) {
            App.mostrarAlerta("Formato de Tarifa Base Inválido",
                    "Por favor, ingresa un número válido para la tarifa base.");
            return;
        }

        try {
            int numeroPuertas = Integer.parseInt(numeroPuertasCadena);
        } catch (NumberFormatException e) {
            App.mostrarAlerta("Formato de número puertas Inválido",
                    "Por favor, ingresa un número válido para el número de puertas.");
            return;
        }

        Auto auto = crearAuto();

        if (autoController.agregarVehiculo(auto)) {
            // Actualiza la tabla
            setAutos();

            // Limpiar campos después de agregar
            limpiarCampos();
        } else {
            App.mostrarAlerta("Error", "El vehículo con número de matrícula " + txtMatricula.getText() + " ya existe");
        }

    }

    public Auto crearAuto() {
        return new Auto(txtMatricula.getText(), txtMarca.getText(), txtModelo.getText(),
                datePickerFechaFabricacion.getValue(), Integer.parseInt(txtNumeroPuertas.getText()), null,
                Integer.parseInt(txtTarifaBase.getText()));
    }

    // Método para eliminar una auto seleccionada
    @FXML
    public void eliminarAuto(ActionEvent event) {
        Auto autoSeleccionado = tblListAuto.getSelectionModel().getSelectedItem();
        if (autoSeleccionado == null) {
            App.mostrarAlerta("No hay auto seleccionada", "Por favor, selecciona una auto para eliminar.");
            return;
        }

        autoController.eliminarVehiculo(autoSeleccionado);

        // Se limpian los campos
        limpiarCampos();
    }

    // Método para actualizar los datos de una auto seleccionada
    @FXML
    public void actualizarAuto(ActionEvent event) {
        if (autoSeleccionado == null) {
            App.mostrarAlerta("No hay auto seleccionada", "Por favor, selecciona una auto para actualizar.");
            return;
        }

        String matricula = txtMatricula.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        LocalDate fechaFabricacion = datePickerFechaFabricacion.getValue();
        String tarifaBaseCadena = txtTarifaBase.getText();
        String numeroPuertasCadena = txtNumeroPuertas.getText();

        if (camposVacios(matricula, marca, modelo, fechaFabricacion, tarifaBaseCadena, numeroPuertasCadena)) {
            App.mostrarAlerta("Campos vacíos", "Por favor llene todos los campos");
            return;
        }

        double tarifaBase;
        try {
            tarifaBase = Double.parseDouble(tarifaBaseCadena);
        } catch (NumberFormatException e) {
            App.mostrarAlerta("Formato de Tarifa Base Inválido",
                    "Por favor, ingresa un número válido para la tarifa base.");
            return;
        }

        int numeroPuertas;
        try {
            numeroPuertas = Integer.parseInt(numeroPuertasCadena);
        } catch (NumberFormatException e) {
            App.mostrarAlerta("Formato de Tarifa Base Inválido",
                    "Por favor, ingresa un número válido para la tarifa base.");
            return;
        }

        if (autoController.actualizarAuto(autoSeleccionado, matricula, marca, modelo, fechaFabricacion, numeroPuertas,
                tarifaBase)) {
            // Refrescar la tabla para mostrar los cambios
            tblListAuto.refresh();

            // Limpiar los campos después de actualizar
            limpiarCampos();

            limpiarSeleccion();
        } else {
            App.mostrarAlerta("Error", "Ya existe un auto con el número de matrícula " + matricula);
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
        txtNumeroPuertas.clear();

        // Deseleccionar la auto en la tabla
        tblListAuto.getSelectionModel().clearSelection();
    }

    private void limpiarSeleccion() {
        tblListAuto.getSelectionModel().clearSelection(); // Deseleccionar el cliente en la tabla
        autoSeleccionado = null; // Reiniciar la referencia al cliente seleccionado
    }
}
