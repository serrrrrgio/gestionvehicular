package co.edu.uniquindio.poo.Model;

import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class Moto extends Vehiculo {
    private SimpleObjectProperty<TipoCaja> tipoCaja;

    // Constructor de Moto ajustado a la firma solicitada
    public Moto(String matricula, String marca, String modelo, LocalDate fechaFabricacion, Reserva reserva, double tarifaBase, TipoCaja tipoCaja) {
        super(matricula, marca, modelo, fechaFabricacion, reserva, tarifaBase); // Llamada al constructor de Vehiculo
        this.tipoCaja = new SimpleObjectProperty<>(tipoCaja);
    }

    @Override
    public double calcularCosto() {
        return getTarifaBase(); // Si necesitas agregar una tarifa adicional, puedes incluirla aquí
    }

    // Métodos getter y setter de propiedad
    public TipoCaja getTipoCaja() {
        return tipoCaja.get();
    }

    public void setTipoCaja(TipoCaja tipoCaja) {
        this.tipoCaja.set(tipoCaja);
    }

    public SimpleObjectProperty<TipoCaja> tipoCajaProperty() {
        return tipoCaja;
    }

}

