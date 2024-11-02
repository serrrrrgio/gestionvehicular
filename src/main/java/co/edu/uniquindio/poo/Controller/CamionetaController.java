package co.edu.uniquindio.poo.Controller;

import java.time.LocalDate;

import co.edu.uniquindio.poo.Model.Camioneta;
import co.edu.uniquindio.poo.Model.Empresa;
import co.edu.uniquindio.poo.Model.Vehiculo;
import javafx.collections.ObservableList;

public class CamionetaController {
    public Empresa empresa;

    public CamionetaController(Empresa empresa) {
        this.empresa = empresa;
    }

        public ObservableList<Camioneta> obtenerCamionetas(){
        return empresa.getCamionetas();
    }

    public boolean actualizarCamioneta(Camioneta seleccionada, String numeroMatricula, String marca, String modelo,
            LocalDate fechaFabricacion,
            double capacidadCargaToneladas, double tarifaBase, double porcentaje) {
        return empresa.actualizarCamioneta(seleccionada, numeroMatricula, marca, modelo, fechaFabricacion,
                capacidadCargaToneladas, tarifaBase, porcentaje);
    }

    public boolean agregarVehiculo(Vehiculo vehiculo) {
        return empresa.agregarVehiculo(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        empresa.eliminarVehiculo(vehiculo);
    }
}
