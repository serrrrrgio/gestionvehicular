package co.edu.uniquindio.poo.Controller;

import co.edu.uniquindio.poo.Model.Cliente;
import co.edu.uniquindio.poo.Model.Empresa;
import co.edu.uniquindio.poo.Model.Vehiculo;
import javafx.collections.ObservableList;

public class ClienteController {
    public Empresa empresa;

    public ClienteController(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean agregarCliente(Cliente cliente) {
        return empresa.agregarCliente(cliente);
    }

    public void eliminarCliente(Cliente cliente) {
        empresa.eliminarCliente(cliente);
    }

    public boolean actualizarCliente(Cliente seleccionado, String nombre, String telefono) {
        return empresa.actualizarCliente(seleccionado, nombre, telefono);
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        empresa.agregarVehiculo(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        empresa.eliminarVehiculo(vehiculo);
    }

    public ObservableList<Cliente> obtenerCLientes() {
        return empresa.getClientes();
    }
}
