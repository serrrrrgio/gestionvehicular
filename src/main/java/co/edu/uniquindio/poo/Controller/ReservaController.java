package co.edu.uniquindio.poo.Controller;

import java.time.LocalDate;

import co.edu.uniquindio.poo.Model.Empresa;
import co.edu.uniquindio.poo.Model.Reserva;
import co.edu.uniquindio.poo.Model.Vehiculo;
import javafx.collections.ObservableList;

public class ReservaController {
    Empresa empresa;

    public ReservaController(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public ObservableList<Vehiculo> obtenerVehiculos(){
        return empresa.getVehiculos();
    }

    public void eliminarVehiculo(Vehiculo vehiculo){
        empresa.eliminarVehiculo(vehiculo);
    }

    public boolean agregarReserva(Reserva reserva){
        return empresa.agregarReserva(reserva);
    }

    public int calcularDias(LocalDate fecha1, LocalDate fecha2){
        return empresa.calcularDias(fecha1, fecha2);
    }

    public boolean validarFechaPosterior(LocalDate fecha1, LocalDate fecha2){
        return empresa.validarFechaPosterior(fecha1, fecha2);
    }
    
}
