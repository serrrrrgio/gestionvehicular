package co.edu.uniquindio.poo.Model;

import javafx.beans.property.SimpleIntegerProperty;

import java.time.LocalDate;

public class Auto extends Vehiculo {
    private SimpleIntegerProperty numeroPuertas;

    public Auto(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion, int numeroPuertas,
                Reserva reserva, double tarifaBase) {
        super(numeroMatricula, marca, modelo, fechaFabricacion, reserva, tarifaBase);
        this.numeroPuertas = new SimpleIntegerProperty(numeroPuertas);
    }

    public int getNumeroPuertas() {
        return (int) numeroPuertas.get();
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas.set(numeroPuertas);
    }

    public SimpleIntegerProperty numeroPuertasProperty() {
        return numeroPuertas;
    }

    @Override
    public double calcularCosto(int dias) {
        return getTarifaBase() * dias;
    }
}
