package co.edu.uniquindio.poo.ViewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Controller.ClienteController;
import co.edu.uniquindio.poo.Model.Cliente;

public class ClienteViewController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private Button btnAgregarCliente;

    @FXML
    private Button btnActualizarCliente;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private Button btnAgregarReserva; // Botón para agregar reserva

    @FXML
    private Button btnSalir;

    @FXML
    private TableView<Cliente> tblListCliente;

    @FXML
    private TableColumn<Cliente, String> tbcNombre;

    @FXML
    private TableColumn<Cliente, String> tbcTelefono;

    @FXML
    private TableColumn<Cliente, String> tbcReservas;

    Cliente clienteSeleccionado;

    private ClienteController clienteController;

    @FXML
    public void initialize() {
        clienteController = new ClienteController(App.getEmpresa());

        setClientes();

        inicializarData();

        agregarListener();
    }

    // Agregar listener para detectar selección de cliente
    private void agregarListener() {

        tblListCliente.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            clienteSeleccionado = newValue; //
            mostrarInformacionCliente(newValue);
        });
    }

    // Inicializar los datos de las columnas de la tabla
    private void inicializarData() {
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tbcReservas.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getReservas().size())));
    }

    // Mostrar la información del cliente seleccionado
    private void mostrarInformacionCliente(Cliente cliente) {
        if (cliente != null) {
            txtNombre.setText(cliente.getNombre());
            txtTelefono.setText(cliente.getTelefono());
        } else {
            // Limpiar campos si no hay cliente seleccionado
            limpiarCampos();
        }
    }

    // Establece la tabla para mostrar la lista de clientes
    public void setClientes() {
        tblListCliente.setItems(clienteController.obtenerCLientes());
    }

    @FXML
    public void agregarCliente(ActionEvent event) {
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            App.mostrarAlerta("Error", "Debe completar todos los campos");
            return;
        }

        Cliente cliente = crearCliente();

        // Verificar si el cliente ya existe
        if (clienteController.agregarCliente(cliente)) {
            setClientes();
            tblListCliente.refresh();
            limpiarCampos();
        } else {
            App.mostrarAlerta("Error", "Ya existe un cliente con el número de teléfono " + txtTelefono.getText());
        }

    }

    public Cliente crearCliente() {
        return new Cliente(txtNombre.getText(), txtTelefono.getText());
    }

    @FXML
    public void actualizarCliente(ActionEvent event) {
        if (clienteSeleccionado == null) {
            App.mostrarAlerta("Error", "Debe seleccionar un cliente para actualizar");
            return;
        }

        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            App.mostrarAlerta("Error", "Debe completar todos los campos");
            return;
        }

        if (clienteController.actualizarCliente(clienteSeleccionado, nombre, telefono)) {
            setClientes();
            tblListCliente.refresh(); // Refrescar la tabla para mostrar los cambios
            limpiarCampos(); // Llamar a limpiar campos después de actualizar
        }

    }

    @FXML
    public void eliminarCliente(ActionEvent event) {
        if (clienteSeleccionado == null) {
            App.mostrarAlerta("Error", "Debe seleccionar un cliente para eliminar");
            return;
        }

        clienteController.eliminarCliente(clienteSeleccionado);
        limpiarCampos(); // Llamar a limpiar campos después de eliminar
        limpiarSeleccion(); // Limpiar selección
    }

    @FXML
    public void abrirAgregarReserva(ActionEvent event) {
        if(clienteSeleccionado != null){
            ReservaViewController.setCliente(clienteSeleccionado);
            App.cambiarEscena("/co/edu/uniquindio/poo/Reserva.fxml", "Elegir Vehículo", event, getClass());
        }
        else{
            App.mostrarAlerta("Error", "Debe seleccionar un cliente para realizar una reserva");
        }
        
    }

    @FXML
    public void salirAplicacion(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/Inicio.fxml", "Inicio", event, getClass());
    }

    @FXML
    private void limpiarCampos() {
        txtNombre.clear(); // Limpiar el campo de texto para el nombre
        txtTelefono.clear(); // Limpiar el campo de texto para el teléfono
        limpiarSeleccion(); // Deseleccionar el cliente en la tabla
    }

    private void limpiarSeleccion() {
        tblListCliente.getSelectionModel().clearSelection(); // Deseleccionar el cliente en la tabla
        clienteSeleccionado = null; // Reiniciar la referencia al cliente seleccionado
    }

}
