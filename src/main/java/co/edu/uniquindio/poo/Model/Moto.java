package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;

public class Moto extends Vehiculo {

    private TipoCaja tipoCaja;
    private double tarifaAdicional;

    public Moto(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion,
            Reserva reserva, double tarifaBase, TipoCaja tipoCaja) {
        super(numeroMatricula, marca, modelo, fechaFabricacion, reserva, tarifaBase);
        this.tipoCaja = tipoCaja;

        if (tipoCaja.equals(TipoCaja.AUTOMATICA)){
            this.tarifaAdicional = 2000;
        }
        else{
            this.tarifaAdicional = 0;
        }
    }

    public TipoCaja getTipoCaja() {
        return tipoCaja;
    }

    public void setTipoCaja(TipoCaja tipoCaja) {
        this.tipoCaja = tipoCaja;
    }

    
    public double getTarifaAdicional() {
        return tarifaAdicional;
    }

    public void setTarifaAdicional(double tarifaAdicional) {
        this.tarifaAdicional = tarifaAdicional;
    }

    @Override
    public double calcularCosto() {
        return tarifaBase + tarifaAdicional;
    }
}
