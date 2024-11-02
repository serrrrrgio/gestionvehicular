package co.edu.uniquindio.poo.Controller;

import java.time.LocalDate;

import co.edu.uniquindio.poo.Model.Auto;
import co.edu.uniquindio.poo.Model.Empresa;
import co.edu.uniquindio.poo.Model.Vehiculo;
import javafx.collections.ObservableList;

public class AutoController {
    public Empresa empresa;

    public AutoController(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean agregarVehiculo(Vehiculo vehiculo) {
        return empresa.agregarVehiculo(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        empresa.eliminarVehiculo(vehiculo);
    }

    public boolean actualizarAuto(Auto seleccionado, String numeroMatricula, String marca, String modelo,
            LocalDate fechaFabricacion, int numeroPuertas, double tarifaBase) {
        return empresa.actualizarAuto(seleccionado, numeroMatricula, marca, modelo, fechaFabricacion, numeroPuertas,
                tarifaBase);
    }

    public ObservableList<Auto> obtenerAutos() {
        return empresa.getAutos();
    }
}
