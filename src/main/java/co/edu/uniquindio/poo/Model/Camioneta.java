package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;

public class Camioneta extends Vehiculo {
    private double capacidadCargaToneladas;

    public Camioneta(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion,
            double capacidadCargaToneladas, Reserva reserva, double tarifaBase) {
        super(numeroMatricula, marca, modelo, fechaFabricacion, reserva, tarifaBase);
        this.capacidadCargaToneladas = capacidadCargaToneladas;
    }

    public double getCapacidadCargaToneladas() {
        return capacidadCargaToneladas;
    }

    public void setCapacidadCargaToneladas(double capacidadCargaToneladas) {
        this.capacidadCargaToneladas = capacidadCargaToneladas;
    }

    @Override
    public void calcularCosto() {

    }

}
