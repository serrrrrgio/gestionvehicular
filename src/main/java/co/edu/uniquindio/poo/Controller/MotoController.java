package co.edu.uniquindio.poo.Controller;

import java.time.LocalDate;

import co.edu.uniquindio.poo.Model.Empresa;
import co.edu.uniquindio.poo.Model.Moto;
import co.edu.uniquindio.poo.Model.TipoCaja;
import co.edu.uniquindio.poo.Model.Vehiculo;
import javafx.collections.ObservableList;

public class MotoController {
    public Empresa empresa;

    public MotoController(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean agregarVehiculo(Vehiculo vehiculo) {
        return empresa.agregarVehiculo(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        empresa.eliminarVehiculo(vehiculo);
    }

    public boolean actualizarMoto(Moto seleccionada, String matricula, String marca, String modelo,
            LocalDate fechaFabricacion,
            double tarifaBase, TipoCaja tipoCaja) {
        return empresa.actualizarMoto(seleccionada, matricula, marca, modelo, fechaFabricacion, tarifaBase, tipoCaja);
    }

    public ObservableList<Moto> obtenerMotos(){
        return empresa.getMotos();
    }



}
