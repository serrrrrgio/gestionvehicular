package co.edu.uniquindio.poo.Model;

public class Reserva {
    private int dias;
    private String codigo;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Reserva(int dias, String codigo, Cliente cliente, Vehiculo vehiculo) {
        this.dias = dias;
        this.codigo = codigo;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
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

}
