package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;

public class Auto extends Vehiculo {
    private int numeroPuertas;

    public Auto(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion, int numeroPuertas,
            Reserva reserva, double tarifaBase) {
        super(numeroMatricula, marca, modelo, fechaFabricacion, reserva, tarifaBase);
        this.numeroPuertas = numeroPuertas;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

    @Override
    public double calcularCosto() {
        return tarifaBase * reserva.getDias();
    }



}
