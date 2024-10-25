package co.edu.uniquindio.poo.Model;

import java.util.LinkedList;

public class Cliente {
    private String nombre;
    private String telfono;
    private LinkedList<Reserva> reservas;


    public Cliente(String nombre, String telfono) {
        this.nombre = nombre;
        this.telfono = telfono;
        this.reservas = new LinkedList<>();
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getTelfono() {
        return telfono;
    }


    public void setTelfono(String telfono) {
        this.telfono = telfono;
    }


    public LinkedList<Reserva> getReservas() {
        return reservas;
    }


    public void setReservas(LinkedList<Reserva> reservas) {
        this.reservas = reservas;
    }

    
}
