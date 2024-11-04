package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private LocalDate fechaEntrega;
    private LocalDate fechaDevolucion;
    private String codigo;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Reserva(LocalDate fechaEntrega, LocalDate fechaDevolucion, String codigo, Cliente cliente, Vehiculo vehiculo) {
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
        this.codigo = codigo;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public int calcularDias(){
        return (int) ChronoUnit.DAYS.between(fechaEntrega, fechaDevolucion);
    }

}
