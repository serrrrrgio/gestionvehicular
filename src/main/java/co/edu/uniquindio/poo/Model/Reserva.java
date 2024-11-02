package co.edu.uniquindio.poo.Model;

public class Reserva {
    private int dias;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Reserva(int dias, Cliente cliente, Vehiculo vehiculo) {
        this.dias = dias;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
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

}
