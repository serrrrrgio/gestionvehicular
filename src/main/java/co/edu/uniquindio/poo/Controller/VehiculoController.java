package co.edu.uniquindio.poo.Controller;

import java.time.LocalDate;

import co.edu.uniquindio.poo.Model.Auto;
import co.edu.uniquindio.poo.Model.Camioneta;
import co.edu.uniquindio.poo.Model.Empresa;
import co.edu.uniquindio.poo.Model.Moto;
import co.edu.uniquindio.poo.Model.TipoCaja;
import co.edu.uniquindio.poo.Model.Vehiculo;
import javafx.collections.ObservableList;

public class VehiculoController {
    public Empresa empresa;

    public VehiculoController(Empresa empresa) {
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

    public boolean actualizarAuto(Auto seleccionado, String numeroMatricula, String marca, String modelo,
            LocalDate fechaFabricacion, int numeroPuertas, double tarifaBase) {
        return empresa.actualizarAuto(seleccionado, numeroMatricula, marca, modelo, fechaFabricacion, numeroPuertas,
                tarifaBase);
    }

    public boolean actualizarCamioneta(Camioneta seleccionada, String numeroMatricula, String marca, String modelo,
            LocalDate fechaFabricacion,
            double capacidadCargaToneladas, double tarifaBase, double porcentaje) {
        return empresa.actualizarCamioneta(seleccionada, numeroMatricula, marca, modelo, fechaFabricacion,
                capacidadCargaToneladas, tarifaBase, porcentaje);
    }

    public ObservableList<Vehiculo> obtenerVehiculos(){
        return empresa.getVehiculos();
    }

    public ObservableList<Moto> obtenerMotos(){
        return empresa.getMotos();
    }

    public ObservableList<Camioneta> obtenerCamionetas(){
        return empresa.getCamionetas();
    }

    public ObservableList<Auto> obtenerAutos(){
        return empresa.getAutos();
    }


}
