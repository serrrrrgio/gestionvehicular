package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;

public class Camioneta extends Vehiculo {
    private double capacidadCargaToneladas;
    public double porcentaje;

    public Camioneta(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion,
            double capacidadCargaToneladas, Reserva reserva, double tarifaBase, double porcentaje) {
        super(numeroMatricula, marca, modelo, fechaFabricacion, reserva, tarifaBase);
        this.capacidadCargaToneladas = capacidadCargaToneladas;
        this.porcentaje = porcentaje;
    }

    public double getCapacidadCargaToneladas() {
        return capacidadCargaToneladas;
    }

    public void setCapacidadCargaToneladas(double capacidadCargaToneladas) {
        this.capacidadCargaToneladas = capacidadCargaToneladas;
    }

    @Override
    public double calcularCosto() {

        return tarifaBase + (capacidadCargaToneladas * (porcentaje/100));
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

}
