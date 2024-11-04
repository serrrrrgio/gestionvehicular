package co.edu.uniquindio.poo.ViewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Controller.ReservaController;
import co.edu.uniquindio.poo.Model.Reserva;
import co.edu.uniquindio.poo.Model.Vehiculo;
import co.edu.uniquindio.poo.Model.Cliente;

public class ReservaViewController {

    @FXML
    private TableView<Vehiculo> tblListVehiculosDisponibles;

    @FXML
    private TableColumn<Vehiculo, String> tbcVehiculo;

    @FXML
    private TableColumn<Vehiculo, String> tbcMarca;

    @FXML
    private TableColumn<Vehiculo, String> tbcModelo;

    @FXML
    private TableColumn<Vehiculo, LocalDate> tbcFechaFabricacion;

    @FXML
    private TableColumn<Vehiculo, Double> tbcTarifaBase;

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtDias;

    @FXML
    private TextField txtCostoReserva;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtVehiculo;

    @FXML
    private Button btnCalcularCostoReserva;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnAgregarReserva;

    @FXML
    private Button btnCalcularDias;

    @FXML
    private DatePicker datePickerFechaEntrega;

    @FXML
    private DatePicker datePickerFechaDevolucion;

    private Vehiculo vehiculoSeleccionado;

    private ReservaController reservaController;

    private static Cliente cliente;

    private boolean costoCalculado;

    @FXML
    public void initialize() {
        costoCalculado = false;
        reservaController = new ReservaController(App.getEmpresa());
        setVehiculos();
        inicializarData();
        agregarListener();
        txtCliente.setText(cliente.getNombre());
    }

    public void agregarListener() {
        tblListVehiculosDisponibles.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    vehiculoSeleccionado = newValue;
                    txtVehiculo.setText(
                            vehiculoSeleccionado.getClass().getSimpleName() + ": " + vehiculoSeleccionado.getModelo());
                });

    }

    public void inicializarData() {
        tbcVehiculo.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
        tbcMarca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        tbcModelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
        tbcFechaFabricacion.setCellValueFactory(cellData -> cellData.getValue().fechaFabricacionProperty());
        tbcTarifaBase.setCellValueFactory(cellData -> cellData.getValue().tarifaBaseProperty().asObject());
    }

    @FXML
    public void generarReserva(ActionEvent event) {
        Reserva reserva = crearReserva();
        if (validarReserva(reserva)) {
            if (reservaController.agregarReserva(reserva)) {
                cliente.agregarReserva(reserva);
                vehiculoSeleccionado.setReserva(reserva);
                reservaController.eliminarVehiculo(vehiculoSeleccionado);
                App.mostrarMensaje("Reserva creada", "", "La reserva se ha creado correctamente");
                App.cambiarEscena("/co/edu/uniquindio/poo/Cliente.fxml", "Agregar cliente", event,
                        getClass());
            }
            else{
                App.mostrarAlerta("Error", "Ya existe una reserva con el mismo código");
            }
        }

    }

    public boolean validarReserva(Reserva reserva) {
        if (!costoCalculado) {
            App.mostrarAlerta("Error", "Por favor calcule el costo de su reserva");
            return false;
        }

        else if (txtCodigo.getText().isEmpty()) {
            App.mostrarAlerta("Error", "Por favor ingrese el codigo de la reserva");
            return false;
        }
        return true;

    }

    public Reserva crearReserva() {
        return new Reserva(datePickerFechaEntrega.getValue(), datePickerFechaDevolucion.getValue(), txtCodigo.getText(),
                cliente, vehiculoSeleccionado);
    }

    public boolean validarDias(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método para establecer la lista de motos
    public void setVehiculos() {
        tblListVehiculosDisponibles.setItems(reservaController.obtenerVehiculos());
    }

    @FXML
    public void calcularCosto() {
        if (vehiculoSeleccionado == null) {
            App.mostrarAlerta("Vehículo no seleccionado", "Por favor seleccione un vehículo para realizar la reserva");
            return;
        }

        else if (!validarDias(txtDias.getText())) {
            App.mostrarAlerta("Días inválidos", "Por favor, calcule la cantidad de días de su reserva");
            return;
        }

        int dias = Integer.parseInt(txtDias.getText());
        double costo = vehiculoSeleccionado.calcularCosto(dias);
        txtCostoReserva.setText(String.valueOf(costo));
        costoCalculado = true;
    }

    @FXML
    public boolean calcularDias() {
        if (datePickerFechaEntrega.getValue() == null || datePickerFechaDevolucion.getValue() == null) {
            App.mostrarAlerta("Error", "Elija una fecha de entrega y una fecha de devolución");
            return false;
        }

        if (_calcularDias(datePickerFechaEntrega.getValue(), datePickerFechaDevolucion.getValue()) < 0) {
            App.mostrarAlerta("Error", "La fecha de devolución debe de ser posterior a la de entrega");
            return false;
        }

        txtDias.setText(_calcularDias(datePickerFechaEntrega.getValue(), datePickerFechaDevolucion.getValue()) + "");
        return true;
    }

    public int _calcularDias(LocalDate fecha1, LocalDate fecha2){
        return (int) ChronoUnit.DAYS.between(fecha1, fecha2);
    }

    @FXML
    public void regresar(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/Cliente.fxml", "Agregar cliente", event,
                getClass());
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente cliente) {
        ReservaViewController.cliente = cliente;
    }

}
