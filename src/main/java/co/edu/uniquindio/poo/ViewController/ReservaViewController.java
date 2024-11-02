package co.edu.uniquindio.poo.ViewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Model.Reserva;
import co.edu.uniquindio.poo.Model.Vehiculo;
import co.edu.uniquindio.poo.Model.Cliente;
import co.edu.uniquindio.poo.Model.Empresa;

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
    private Button btnCalcularCostoReserva;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnAgregarReserva;

    private ObservableList<Vehiculo> vehiculos;

    private Vehiculo vehiculoSeleccionado;

    private Empresa empresa;

    private static Cliente cliente;

    @FXML
    public void initialize() {
        empresa = App.getEmpresa();
        vehiculos = empresa.getVehiculos();

        setVehiculos();
        inicializarData();

        // Agregar un listener para la selección de una moto en la tabla
        tblListVehiculosDisponibles.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    vehiculoSeleccionado = newValue;
                });
        txtCliente.setText(cliente.getNombre());
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
        if (validarDias(txtDias.getText())) {
            int dias = Integer.parseInt(txtDias.getText());
            Reserva reserva = new Reserva(dias, cliente, vehiculoSeleccionado);
            cliente.agregarReserva(reserva);
            vehiculoSeleccionado.setReserva(reserva);
            empresa.agregarReserva(reserva);
            empresa.eliminarVehiculo(vehiculoSeleccionado);
            App.mostrarAlerta("Reserva agregada exitosamente", "La reserva se ha agregado");
            App.cambiarEscena("/co/edu/uniquindio/poo/ViewController/AgregarCliente.fxml", "Agregar cliente", event,
                    getClass());
        } else {
            App.mostrarAlerta("Error, días inválidos", "Por favor llene el campo de días");
        }
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
        tblListVehiculosDisponibles.setItems(vehiculos);
    }

    @FXML
    public void calcularCosto() {
        if (validarDias(txtDias.getText())) {
            int dias = Integer.parseInt(txtDias.getText());
            double costo = vehiculoSeleccionado.calcularCosto(dias);
            txtCostoReserva.setText(String.valueOf(costo));
        } else {
            App.mostrarAlerta("Días inválidos", "Por favor llene el campo de días");
            return;
        }

    }

    @FXML
    public void regresar(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/ViewController/AgregarCliente.fxml", "Agregar cliente", event,
                getClass());
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente cliente) {
        ReservaViewController.cliente = cliente;
    }

}
