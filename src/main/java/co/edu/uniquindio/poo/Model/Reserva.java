package co.edu.uniquindio.poo.Model;

public class Reserva {
    private int dias;
    private double costo;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Reserva(int dias) {
        this.dias = dias;
        this.costo = 0;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
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
