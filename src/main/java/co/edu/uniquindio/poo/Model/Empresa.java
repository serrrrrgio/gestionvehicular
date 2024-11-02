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

    public void agregarVehiculo(Vehiculo vehiculo) {
        if (vehiculo instanceof Moto) {
            motos.add((Moto) vehiculo);
        } else if (vehiculo instanceof Camioneta) {
            camionetas.add((Camioneta) vehiculo);
        } else if (vehiculo instanceof Auto) {
            autos.add((Auto) vehiculo);
        }
        vehiculos.add(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        if (vehiculo instanceof Moto) {
            motos.remove((Moto) vehiculo);
        } else if (vehiculo instanceof Camioneta) {
            camionetas.remove((Camioneta) vehiculo);
        } else if (vehiculo instanceof Auto) {
            autos.remove((Auto) vehiculo);
        }
        vehiculos.remove(vehiculo);
    }

    public boolean agregarCliente(Cliente cliente) {
        boolean agregado = false;
        if(!clienteExistente(cliente.getTelefono())){
            clientes.add(cliente);
            agregado = true;
        }
        return agregado;
        
    }

    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public boolean actualizarCliente(Cliente seleccionado, String nombre, String telefono){
        boolean actualizado = true;
        for(Cliente cliente: clientes){
            if(cliente != seleccionado && cliente.getTelefono().equals(telefono)){
                actualizado = false;
                return actualizado;
            }
        }
        seleccionado.setTelefono(telefono);
        seleccionado.setNombre(nombre);
        return actualizado;
    }

    public boolean clienteExistente(String telefono){
        boolean existente = false;
        for (Cliente cliente: clientes){
            if(cliente.getTelefono().equals(telefono)){
                existente = true;
                break;
            }
        }
        return existente;
    }


    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

}
