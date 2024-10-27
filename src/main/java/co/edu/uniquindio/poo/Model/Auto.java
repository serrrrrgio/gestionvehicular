package co.edu.uniquindio.poo.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.time.LocalDate;

public class Auto extends Vehiculo {
    private SimpleDoubleProperty numeroPuertas;

    public Auto(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion, int numeroPuertas,
                Reserva reserva, double tarifaBase) {
        super(numeroMatricula, marca, modelo, fechaFabricacion, reserva, tarifaBase);
        this.numeroPuertas = new SimpleDoubleProperty(numeroPuertas);
    }

    public int getNumeroPuertas() {
        return (int) numeroPuertas.get();
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas.set(numeroPuertas);
    }

    public SimpleDoubleProperty numeroPuertasProperty() {
        return numeroPuertas;
    }

    @Override
    public double calcularCosto() {
        return getTarifaBase() * getReserva().getDias();
    }
}
