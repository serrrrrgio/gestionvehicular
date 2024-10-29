package co.edu.uniquindio.poo.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Model.Cliente;
import javafx.fxml.FXMLLoader;

public class AgregarClienteController {


    private ObservableList<Cliente> clientes;

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

    private Cliente clienteSeleccionado; // Variable para almacenar el cliente seleccionado

    @FXML
    public void initialize() {
        clientes = App.getEmpresa().getClientes();
        setClientes();
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tbcReservas.setCellValueFactory(new PropertyValueFactory<>("reservas"));

        // Agregar listener para detectar selección de cliente
        tblListCliente.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            clienteSeleccionado = newValue; // Actualizar clienteSeleccionado
            mostrarInformacionCliente(clienteSeleccionado); // Mostrar información en los campos
        });

        // Aplicar el TextFormatter al txtTelefono para permitir solo números
        txtTelefono.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) { // Acepta solo dígitos
                return change; // Permite el cambio
            }
            return null; // Rechaza el cambio
        }));
    }


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
        tblListCliente.setItems(clientes); 
    }

    @FXML
    public void agregarCliente(ActionEvent event) {
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            App.mostrarAlerta("Error", "Debe completar todos los campos");
            return;
        }

        // Verificar si el cliente ya existe
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombre) || cliente.getTelefono().equals(telefono)) {
                App.mostrarAlerta("Error", "El cliente ya está registrado");
                return;
            }
        }

        Cliente cliente = new Cliente(nombre, telefono);
        clientes.add(cliente);
        tblListCliente.refresh();
        limpiarCampos();
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

        // Verificar si el nuevo nombre o teléfono ya existe
        for (Cliente cliente : clientes) {
            if(!cliente.equals(clienteSeleccionado)){
                if (!cliente.equals(clienteSeleccionado) && (cliente.getNombre().equals(nombre) || cliente.getTelefono().equals(telefono))) {
                    App.mostrarAlerta("Error", "El cliente ya está registrado");
                    return;
                } 
            }
        }

        // Actualizar los datos del cliente seleccionado
        clienteSeleccionado.setNombre(txtNombre.getText());
        clienteSeleccionado.setTelefono(txtTelefono.getText());

        // Actualizar la tabla
        tblListCliente.refresh(); // Refrescar la tabla para mostrar los cambios
        limpiarCampos(); // Llamar a limpiar campos después de actualizar
    }

    @FXML
    public void eliminarCliente(ActionEvent event) {
        if (clienteSeleccionado == null) {
            App.mostrarAlerta("Error", "Debe seleccionar un cliente para eliminar");
            return;
        }

        // Eliminar el cliente seleccionado
        clientes.remove(clienteSeleccionado);
        limpiarCampos(); // Llamar a limpiar campos después de eliminar
        limpiarSeleccion(); // Limpiar selección
    }

    @FXML
    public void abrirAgregarReserva(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/ViewController/Reserva.fxml", "Elegir Vehículo", event, getClass());
    }

    @FXML
    public void salirAplicacion(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/ViewController/Inicio.fxml", "Inicio", event, getClass());
    }

    @FXML
    private void limpiarCampos() {
        txtNombre.clear();  // Limpiar el campo de texto para el nombre
        txtTelefono.clear(); // Limpiar el campo de texto para el teléfono
        limpiarSeleccion();   // Deseleccionar el cliente en la tabla
    }

    private void limpiarSeleccion() {
        tblListCliente.getSelectionModel().clearSelection(); // Deseleccionar el cliente en la tabla
        clienteSeleccionado = null; // Reiniciar la referencia al cliente seleccionado
    }

}
