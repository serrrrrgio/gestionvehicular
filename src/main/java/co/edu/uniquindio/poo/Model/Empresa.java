package co.edu.uniquindio.poo.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Empresa {
    private String nombre;
    private ObservableList<Moto> motos;
    private ObservableList<Auto> autos;
    private ObservableList<Camioneta> camionetas;
    private ObservableList<Cliente> clientes;
    private ObservableList<Reserva> reservas;
    private ObservableList<Vehiculo> vehiculos;



    public Empresa(String nombre) {
        this.nombre = nombre;
        this.motos = FXCollections.observableArrayList();
        this.autos = FXCollections.observableArrayList();
        this.camionetas = FXCollections.observableArrayList();
        this.clientes = FXCollections.observableArrayList();
        this.reservas = FXCollections.observableArrayList();
        this.vehiculos = FXCollections.observableArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public ObservableList<Moto> getMotos() {
        return motos;
    }

    public void setMotos(ObservableList<Moto> motos) {
        this.motos = motos;
    }

    public ObservableList<Auto> getAutos() {
        return autos;
    }

    public void setAutos(ObservableList<Auto> autos) {
        this.autos = autos;
    }

    public ObservableList<Camioneta> getCamionetas() {
        return camionetas;
    }

    public void setCamionetas(ObservableList<Camioneta> camionetas) {
        this.camionetas = camionetas;
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ObservableList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ObservableList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ObservableList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public ObservableList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ObservableList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void agregarVehiculo(Vehiculo vehiculo){
        vehiculos.add(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo){
        vehiculos.remove(vehiculo);
    }

    public void agregarMoto(Moto moto){
        motos.add(moto);
        vehiculos.add(moto);
    }

    public void eliminarMoto(Moto moto){
        motos.remove(moto);
        vehiculos.remove(moto);
    }

    public void agregarAuto(Auto auto){
        autos.add(auto);
        vehiculos.add(auto);
    }

    public void eliminarAuto(Auto auto){
        autos.remove(auto);
        vehiculos.remove(auto);
    }

    public void agregarCamioneta(Camioneta camioneta){
        camionetas.add(camioneta);
        vehiculos.add(camioneta);
    }

    public void eliminarCamioneta(Camioneta camioneta){
        camionetas.remove(camioneta);
        vehiculos.remove(camioneta);
    }

    public void agregarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public void eliminarCliente(Cliente cliente){
        clientes.remove(cliente);
    }

    public void agregarReserva(Reserva reserva){
        reservas.add(reserva);
    }

    public void eliminarReserva(Reserva reserva){
        reservas.remove(reserva);
    }

}
