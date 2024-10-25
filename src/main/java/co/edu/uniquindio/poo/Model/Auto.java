package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;

public class Auto extends Vehiculo {
    private int numeroPuertas;

public Auto(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion, int numeroPuertas, Reserva reserva) {
    super(numeroMatricula, marca, modelo, fechaFabricacion, reserva);
    this.numeroPuertas = numeroPuertas;
}

public int getNumeroPuertas() {
    return numeroPuertas;
}

public void setNumeroPuertas(int numeroPuertas) {
    this.numeroPuertas = numeroPuertas;
}

@Override
public void calcularCosto() {
    // TODO Auto-generated method stub
    
}







    
}
