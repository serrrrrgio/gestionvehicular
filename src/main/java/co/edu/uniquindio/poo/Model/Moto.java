package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;

public class Moto extends Vehiculo {

    private TipoCaja TipoCaja;

    public Moto(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion,
            TipoCaja tipoCaja, Reserva reserva, double tarifaBase) {
        super(numeroMatricula, marca, modelo, fechaFabricacion, reserva, tarifaBase);
        TipoCaja = tipoCaja;
    }

    public TipoCaja getTipoCaja() {
        return TipoCaja;
    }

    public void setTipoCaja(TipoCaja tipoCaja) {
        TipoCaja = tipoCaja;
    }

    @Override
    public double calcularCosto() {

        return 0;
    }



}
