package co.edu.uniquindio.poo.Controller;

import java.time.LocalDate;


import co.edu.uniquindio.poo.Model.TipoCaja;
import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.Model.Moto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MotoController {

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

    private Moto motoSeleccionada;

    private ObservableList<Moto> motos; // La lista de motos

    @FXML
    public void initialize() {
        motos = App.getEmpresa().getMotos();
        setMotos();
        
        inicializarData();

        // Agregar un listener para la selección de una moto en la tabla
        tblListMoto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            motoSeleccionada = newValue;
            mostrarInformacionMoto(newValue);
        });
    }

    
    public void inicializarData(){
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
        tblListMoto.setItems(motos);
    }

    // Método para verificar si los campos están vacíos
    boolean camposVacios(String matricula, String marca, String modelo, LocalDate fechaFabricacion, String tarifa,
            TipoCaja tipoCaja) {
        return matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty() || fechaFabricacion == null
                || tarifa.isEmpty() || tipoCaja == null;
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

        if (camposVacios(matricula, marca, modelo, fechaFabricacion, tarifaBaseCadena, tipoCaja)) {
            mostrarAlerta("Campos vacíos", "Por favor llene todos los campos");
            return;
        }

        double tarifaBase;
        try {
            tarifaBase = Double.parseDouble(tarifaBaseCadena);
        } catch (NumberFormatException e) {
            mostrarAlerta("Formato de Tarifa Base Inválido",
                    "Por favor, ingresa un número válido para la tarifa base.");
            return;
        }

        //Se verifica que no exista una moto con la misma matrícula
        for (Moto moto : motos) {
            if (moto.getNumeroMatricula().equals(matricula)) {
                mostrarAlerta("Error", "La moto ya está registrada");
                return;
            }
        }

        // Crear nueva moto y agregarla a la lista
        Moto nuevaMoto = new Moto(matricula, marca, modelo, fechaFabricacion, null, tarifaBase, tipoCaja);

        // Agregar la moto a la lista de motos
        motos.add(nuevaMoto);

        // Actualiza la tabla
        setMotos();

        // Limpiar campos después de agregar
        limpiarCampos();
    }


    // Método para eliminar una moto seleccionada
    @FXML
    public void eliminarMoto(ActionEvent event) {
        Moto motoSeleccionada = tblListMoto.getSelectionModel().getSelectedItem();
        if (motoSeleccionada == null) {
            mostrarAlerta("No hay moto seleccionada", "Por favor, selecciona una moto para eliminar.");
            return;
        }

        // Se remueve la moto de la lista
        motos.remove(motoSeleccionada); 

        // Se limpian los campos
        limpiarCampos(); 
    }


    // Método para actualizar los datos de una moto seleccionada
    @FXML
    public void actualizarMoto(ActionEvent event) {
        if (motoSeleccionada == null) {
            mostrarAlerta("No hay moto seleccionada", "Por favor, selecciona una moto para actualizar.");
            return;
        }

        String matricula = txtMatricula.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        LocalDate fechaFabricacion = datePickerFechaFabricacion.getValue();
        String tarifaBaseCadena = txtTarifaBase.getText();
        TipoCaja tipoCaja = choiceTipoCaja.getValue();

        if (camposVacios(matricula, marca, modelo, fechaFabricacion, tarifaBaseCadena, tipoCaja)){
            mostrarAlerta("Campos vacíos", "Por favor llene todos los campos");
            return;
        }

        double tarifaBase;
        try {
            tarifaBase = Double.parseDouble(tarifaBaseCadena);
        } catch (NumberFormatException e) {
            mostrarAlerta("Formato de Tarifa Base Inválido",
                    "Por favor, ingresa un número válido para la tarifa base.");
            return;
        }

        //Se verifica que no exista una moto con la misma matrícula
        for (Moto moto : motos) {

            if(!moto.equals(motoSeleccionada)){
                if (moto.getNumeroMatricula().equals(matricula)) {
                    mostrarAlerta("Error", "Ya existe una moto con este número de matrícula");
                    return;
                }
            }
        }

        // Actualizar los datos de la moto seleccionada
        motoSeleccionada.setNumeroMatricula(txtMatricula.getText());
        motoSeleccionada.setMarca(txtMarca.getText());
        motoSeleccionada.setModelo(txtModelo.getText());
        motoSeleccionada.setFechaFabricacion(datePickerFechaFabricacion.getValue());
        motoSeleccionada.setTarifaBase(tarifaBase);
        motoSeleccionada.setTipoCaja(choiceTipoCaja.getValue());

        // Refrescar la tabla para mostrar los cambios
        tblListMoto.refresh(); 

        // Limpiar los campos después de actualizar
        limpiarCampos(); 
    }


    // Método para regresar a la escena anterior
    @FXML
    public void regresar(ActionEvent event) {
        App.cambiarEscena("/co/edu/uniquindio/poo/ViewController/ElegirVehiculo.fxml", "Elegir Vehículo", event, getClass());
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

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
