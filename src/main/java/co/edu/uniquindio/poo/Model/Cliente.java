package co.edu.uniquindio.poo.Model;

public class Cliente {
    private String nombre;
    private String telfono;
    private Reserva reserva;


    public Cliente(String nombre, String telfono, Reserva reserva) {
        this.nombre = nombre;
        this.telfono = telfono;
        this.reserva = reserva;
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


    public Reserva getReserva() {
        return reserva;
    }


    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }   
}
