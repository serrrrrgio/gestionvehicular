package co.edu.uniquindio.poo.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import co.edu.uniquindio.poo.Model.Cliente;
import co.edu.uniquindio.poo.Model.Moto;
import co.edu.uniquindio.poo.Model.Empresa;

public class MainController {

    private ObservableList<Cliente> clientes; // Lista de clientes
    private ObservableList<Moto> motos; // Lista de motos
    private Empresa empresa;

    public MainController() {
        this.empresa = null;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    


}
