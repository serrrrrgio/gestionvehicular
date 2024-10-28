package co.edu.uniquindio.poo.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import co.edu.uniquindio.poo.Model.Cliente;
import co.edu.uniquindio.poo.Model.Moto;

public class MainController {

    private ObservableList<Cliente> clientes; // Lista de clientes
    private ObservableList<Moto> motos; // Lista de motos

    public MainController() {
        // Inicializa las listas
        this.clientes = FXCollections.observableArrayList();
        this.motos = FXCollections.observableArrayList(); // Inicializa la lista de motos
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }

    public ObservableList<Moto> getMotos() { // Método para obtener la lista de motos
        return motos;
    }

    public void agregarMoto(Moto moto){
        motos.add(moto);
    }

    public void eliminarMoto(Moto moto){
        motos.remove(moto);
    }

    public void agregarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public void eliminarCliente(Cliente cliente){
        clientes.remove(cliente);
    }


}
