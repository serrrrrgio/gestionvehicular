package co.edu.uniquindio.poo.Model;

import javafx.beans.property.SimpleDoubleProperty;

import java.time.LocalDate;

public class Camioneta extends Vehiculo {
    private SimpleDoubleProperty capacidadCargaToneladas;
    private SimpleDoubleProperty porcentaje;

    public Camioneta(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion,
            double capacidadCargaToneladas, Reserva reserva, double tarifaBase, double porcentaje) {
        super(numeroMatricula, marca, modelo, fechaFabricacion, reserva, tarifaBase);
        this.capacidadCargaToneladas = new SimpleDoubleProperty(capacidadCargaToneladas);
        this.porcentaje = new SimpleDoubleProperty(porcentaje);
    }

    public double getCapacidadCargaToneladas() {
        return capacidadCargaToneladas.get();
    }

    public void setCapacidadCargaToneladas(double capacidadCargaToneladas) {
        this.capacidadCargaToneladas.set(capacidadCargaToneladas);
    }

    public SimpleDoubleProperty capacidadCargaToneladasProperty() {
        return capacidadCargaToneladas;
    }

    public double getPorcentaje() {
        return porcentaje.get();
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje.set(porcentaje);
    }

    public SimpleDoubleProperty porcentajeProperty() {
        return porcentaje;
    }

    @Override
    public double calcularCosto(int dias) {
        return getTarifaBase() + (capacidadCargaToneladas.get() * (porcentaje.get() / 100));
    }
}
