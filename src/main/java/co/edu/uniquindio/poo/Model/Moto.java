package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;

public class Moto extends Vehiculo{


    private TipoCaja TipoCaja;

    public Moto(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion,
            co.edu.uniquindio.poo.Model.TipoCaja tipoCaja) {
        super(numeroMatricula, marca, modelo, fechaFabricacion);
        TipoCaja = tipoCaja;
    }

    public TipoCaja getTipoCaja() {
        return TipoCaja;
    }

    public void setTipoCaja(TipoCaja tipoCaja) {
        TipoCaja = tipoCaja;
    }
    
    public Moto(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion){
        super(numeroMatricula, marca, modelo, fechaFabricacion);


    }

    @Override
    public void calcularCosto() {
        
    }
    
}
