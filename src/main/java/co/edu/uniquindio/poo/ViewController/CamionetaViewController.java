package co.edu.uniquindio.poo.ViewController;

import java.time.LocalDate;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Controller.CamionetaController;
import co.edu.uniquindio.poo.Model.Camioneta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CamionetaViewController {

    @FXML
    private TableView<Camioneta> tblListCamioneta;
    @FXML
    private TableColumn<Camioneta, String> tbcMatricula;
    @FXML
    private TableColumn<Camioneta, String> tbcMarca;
    @FXML
    private TableColumn<Camioneta, String> tbcModelo;
    @FXML
    private TableColumn<Camioneta, LocalDate> tbcFechaFabricacion;
    @FXML
    private TableColumn<Camioneta, Double> tbcTarifaBase;
    @FXML
    private TableColumn<Camioneta, Double> tbcCapacidadCargaToneladas;
    @FXML
    private TableColumn<Camioneta, Double> tbcPorcentaje;

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
    private TextField txtCapacidadCargaToneladas;
    @FXML
    private TextField txtPorcentaje;

    @FXML
    private Button btnAgregarCamioneta;
    @FXML
    private Button btnEliminarCamioneta;
    @FXML
    private Button btnActualizarCamioneta;
    @FXML
    private Button btnRegresar;

    private Camioneta camionetaSeleccionada;
    private CamionetaController camionetaController;

    @FXML
    public void initialize() {
        camionetaController = new CamionetaController(App.getEmpresa());
        setCamionetas();

        inicializarData();

        agregarListener();
    }

    public void agregarListener() {
        // Agregar un listener para la selección de una camioneta en la tabla
        tblListCamioneta.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            camionetaSeleccionada = newValue;
            mostrarInformacionCamioneta(newValue);

        });
    }

    public void inicializarData() {
        tbcMatricula.setCellValueFactory(cellData -> cellData.getValue().numeroMatriculaProperty());
        tbcMarca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        tbcModelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
        tbcFechaFabricacion.setCellValueFactory(cellData -> cellData.getValue().fechaFabricacionProperty());
        tbcTarifaBase.setCellValueFactory(cellData -> cellData.getValue().tarifaBaseProperty().asObject());
        tbcCapacidadCargaToneladas
                .setCellValueFactory(cellData -> cellData.getValue().capacidadCargaToneladasProperty().asObject());
        tbcPorcentaje.setCellValueFactory(cellData -> cellData.getValue().porcentajeProperty().asObject());

    }

    // Método para cargar los datos de la camioneta seleccionada en los campos de
    // texto
    private void mostrarInformacionCamioneta(Camioneta camioneta) {
        if (camioneta != null) {
            txtMatricula.setText(camioneta.getNumeroMatricula());
            txtMarca.setText(camioneta.getMarca());
            txtModelo.setText(camioneta.getModelo());
            datePickerFechaFabricacion.setValue(camioneta.getFechaFabricacion());
            txtTarifaBase.setText(String.valueOf(camioneta.getTarifaBase()));
            txtCapacidadCargaToneladas.setText(String.valueOf(camioneta.getCapacidadCargaToneladas()));
            txtPorcentaje.setText(String.valueOf(camioneta.getPorcentaje()));

        } else {
            limpiarCampos();
        }

    }

    // Método para establecer la lista de camionetas
    public void setCamionetas() {
        tblListCamioneta.setItems(camionetaController.obtenerCamionetas());
    }

    // Método para verificar si los campos están vacíos
    boolean camposVacios(String matricula, String marca, String modelo, LocalDate fechaFabricacion, String tarifa,
            String capacidadCargaToneladas, String porcentaje) {
        return matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty() || fechaFabricacion == null
                || tarifa.isEmpty() || capacidadCargaToneladas.isEmpty() || porcentaje.isEmpty();
    }

    // Método para agregar una nueva camioneta
    @FXML
    public void agregarCamioneta(ActionEvent event) {
        String matricula = txtMatricula.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        LocalDate fechaFabricacion = datePickerFechaFabricacion.getValue();
        String tarifaBaseCadena = txtTarifaBase.getText();
        String capacidadCargaToneladasCadena = txtCapacidadCargaToneladas.getText();
        String porcentajeCadena = txtPorcentaje.getText();

        if (camposVacios(matricula, marca, modelo, fechaFabricacion, tarifaBaseCadena, capacidadCargaToneladasCadena,
                porcentajeCadena)) {
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

        try {
            double capacidadCargaToneladas = Double.parseDouble(capacidadCargaToneladasCadena);
        } catch (NumberFormatException e) {
            App.mostrarAlerta("Formato de Tarifa Base Inválido",
                    "Por favor, ingresa un número válido para la tarifa base.");
            return;
        }

        try {
            double porcentaje = Double.parseDouble(porcentajeCadena);
        } catch (NumberFormatException e) {
            App.mostrarAlerta("Formato de Tarifa Base Inválido",
                    "Por favor, ingresa un número válido para la tarifa base.");
            return;
        }

        Camioneta camioneta = crearCamioneta();

        // Se verifica que no exista una camioneta con la misma matrícula
        if (camionetaController.agregarVehiculo(camioneta)) {
            // Actualiza la tabla
            setCamionetas();

            // Limpiar campos después de agregar
            limpiarCampos();
        }

        else {
            App.mostrarAlerta("Alerta", "Ya existe una camioneta con el número de matrícula " + matricula);
        }
    }

    public Camioneta crearCamioneta() {
        return new Camioneta(txtMatricula.getText(), txtMarca.getText(), txtModelo.getText(),
                datePickerFechaFabricacion.getValue(), Double.parseDouble(txtCapacidadCargaToneladas.getText()), null,
                Double.parseDouble(txtTarifaBase.getText()), Double.parseDouble(txtPorcentaje.getText()));
    }

    // Método para eliminar una camioneta seleccionada
    @FXML
    public void eliminarCamioneta(ActionEvent event) {
        Camioneta camionetaSeleccionada = tblListCamioneta.getSelectionModel().getSelectedItem();
        if (camionetaSeleccionada == null) {
            App.mostrarAlerta("No hay camioneta seleccionada", "Por favor, selecciona una camioneta para eliminar.");
            return;
        }

        // Se remueve la camioneta de la lista
        camionetaController.eliminarVehiculo(camionetaSeleccionada);

        // Se limpian los campos
        limpiarCampos();

    }

    // Método para actualizar los datos de una camioneta seleccionada
    @FXML
    public void actualizarCamioneta(ActionEvent event) {
        if (camionetaSeleccionada == null) {
            App.mostrarAlerta("No hay camioneta seleccionada", "Por favor, selecciona una camioneta para actualizar.");
            return;
        }

        String matricula = txtMatricula.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        LocalDate fechaFabricacion = datePickerFechaFabricacion.getValue();
        String tarifaBaseCadena = txtTarifaBase.getText();
        String capacidadCargaToneladasCadena = txtCapacidadCargaToneladas.getText();
        String porcentajeCadena = txtPorcentaje.getText();

        if (camposVacios(matricula, marca, modelo, fechaFabricacion, tarifaBaseCadena, capacidadCargaToneladasCadena,
                porcentajeCadena)) {
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

        double capacidadCargaToneladas;
        try {
            capacidadCargaToneladas = Double.parseDouble(capacidadCargaToneladasCadena);
        } catch (NumberFormatException e) {
            App.mostrarAlerta("Formato de Tarifa Base Inválido",
                    "Por favor, ingresa un número válido para la tarifa base.");
            return;
        }

        double porcentaje;
        try {
            porcentaje = Double.parseDouble(porcentajeCadena);
        } catch (NumberFormatException e) {
            App.mostrarAlerta("Formato de Tarifa Base Inválido",
                    "Por favor, ingresa un número válido para la tarifa base.");
            return;
        }

        if (camionetaController.actualizarCamioneta(camionetaSeleccionada, matricula, marca, modelo, fechaFabricacion,
                capacidadCargaToneladas, tarifaBase, porcentaje)) {
            // Refrescar la tabla para mostrar los cambios
            tblListCamioneta.refresh();
            // Limpiar los campos después de actualizar
            limpiarCampos();
            limpiarSeleccion();
        } else {
            App.mostrarAlerta("Error", "Ya existe una camioneta con el núnmero de matrícula " + matricula);
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
        txtCapacidadCargaToneladas.clear();
        txtPorcentaje.clear();

        // Deseleccionar la camioneta en la tabla
        tblListCamioneta.getSelectionModel().clearSelection();
    }

    private void limpiarSeleccion() {
        tblListCamioneta.getSelectionModel().clearSelection(); // Deseleccionar el cliente en la tabla
        camionetaSeleccionada = null; // Reiniciar la referencia al cliente seleccionado
    }

}
