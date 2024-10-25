package co.edu.uniquindio.poo.Model;

import java.util.LinkedList;

public class Empresa {
    private String nombre;
    private LinkedList<Vehiculo> vehiculos;
    private LinkedList<Cliente> clientes;
    private LinkedList<Reserva> reservas;


    public Empresa(String nombre, LinkedList<Vehiculo> vehiculos, LinkedList<Cliente> clientes,
            LinkedList<Reserva> reservas) {
        this.nombre = nombre;
        this.vehiculos = vehiculos;
        this.clientes = clientes;
        this.reservas = reservas;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public LinkedList<Vehiculo> getVehiculos() {
        return vehiculos;
    }


    public void setVehiculos(LinkedList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }


    public LinkedList<Cliente> getClientes() {
        return clientes;
    }


    public void setClientes(LinkedList<Cliente> clientes) {
        this.clientes = clientes;
    }


    public LinkedList<Reserva> getReservas() {
        return reservas;
    }


    public void setReservas(LinkedList<Reserva> reservas) {
        this.reservas = reservas;
    }

    

    
}
