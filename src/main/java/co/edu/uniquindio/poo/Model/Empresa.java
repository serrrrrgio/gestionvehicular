package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Empresa {
    private String nombre;
    private ObservableList<Moto> motos;
    private ObservableList<Auto> autos;
    private ObservableList<Camioneta> camionetas;
    private ObservableList<Cliente> clientes;
    private ObservableList<Reserva> reservas;
    private ObservableList<Vehiculo> vehiculos;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.motos = FXCollections.observableArrayList();
        this.autos = FXCollections.observableArrayList();
        this.camionetas = FXCollections.observableArrayList();
        this.clientes = FXCollections.observableArrayList();
        this.reservas = FXCollections.observableArrayList();
        this.vehiculos = FXCollections.observableArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ObservableList<Moto> getMotos() {
        return motos;
    }

    public void setMotos(ObservableList<Moto> motos) {
        this.motos = motos;
    }

    public ObservableList<Auto> getAutos() {
        return autos;
    }

    public void setAutos(ObservableList<Auto> autos) {
        this.autos = autos;
    }

    public ObservableList<Camioneta> getCamionetas() {
        return camionetas;
    }

    public void setCamionetas(ObservableList<Camioneta> camionetas) {
        this.camionetas = camionetas;
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ObservableList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ObservableList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ObservableList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public ObservableList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ObservableList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    // Método para agregar una vehículo verificando que no exista
    public boolean agregarVehiculo(Vehiculo vehiculo) {
        boolean agregado = false;
        if (!VehiculoExistente(vehiculo.getNumeroMatricula())) {
            if (vehiculo instanceof Moto) {
                motos.add((Moto) vehiculo);
            } else if (vehiculo instanceof Camioneta) {
                camionetas.add((Camioneta) vehiculo);
            } else if (vehiculo instanceof Auto) {
                autos.add((Auto) vehiculo);
            }
            vehiculos.add(vehiculo);
            agregado = true;
        }
        return agregado;
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        if (vehiculo instanceof Moto) {
            motos.remove((Moto) vehiculo);
        } else if (vehiculo instanceof Camioneta) {
            camionetas.remove((Camioneta) vehiculo);
        } else if (vehiculo instanceof Auto) {
            autos.remove((Auto) vehiculo);
        }
        vehiculos.remove(vehiculo);
    }

    public boolean VehiculoExistente(String numeroMatricula) {
        boolean existente = false;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getNumeroMatricula().equals(numeroMatricula)) {
                existente = true;
                break;
            }
        }
        return existente;
    }

    // Método para actualizar una moto verificando que no exista una con la misma
    // matricula
    public boolean actualizarMoto(Moto seleccionada, String matricula, String marca, String modelo,
            LocalDate fechaFabricacion,
            double tarifaBase, TipoCaja tipoCaja) {
        boolean actualizado = true;
        for (Moto moto : motos) {
            if (moto != seleccionada && moto.getNumeroMatricula().equals(matricula)) {
                actualizado = false;
                return actualizado;
            }
        }
        seleccionada.setNumeroMatricula(matricula);
        seleccionada.setMarca(marca);
        seleccionada.setModelo(modelo);
        seleccionada.setFechaFabricacion(fechaFabricacion);
        seleccionada.setTarifaBase(tarifaBase);
        seleccionada.setTipoCaja(tipoCaja);
        return actualizado;
    }

    // Método para actualizar un auto verificando que no exista uno con la misma
    // matricula
    public boolean actualizarAuto(Auto seleccionado, String numeroMatricula, String marca, String modelo,
            LocalDate fechaFabricacion, int numeroPuertas, double tarifaBase) {
        boolean actualizado = true;
        for (Auto auto : autos) {
            if (auto != seleccionado && auto.getNumeroMatricula().equals(numeroMatricula)) {
                actualizado = false;
                return actualizado;
            }
        }
        seleccionado.setNumeroMatricula(numeroMatricula);
        seleccionado.setMarca(marca);
        seleccionado.setModelo(modelo);
        seleccionado.setFechaFabricacion(fechaFabricacion);
        seleccionado.setNumeroPuertas(numeroPuertas);
        seleccionado.setTarifaBase(tarifaBase);
        return actualizado;
    }

    // Método para actualizar un camioneta verificando que no exista uno con la
    // misma matricula
    public boolean actualizarCamioneta(Camioneta seleccionada, String numeroMatricula, String marca, String modelo,
            LocalDate fechaFabricacion,
            double capacidadCargaToneladas, double tarifaBase, double porcentaje) {
        boolean actualizado = true;
        for (Camioneta camioneta : camionetas) {
            if (camioneta != seleccionada && camioneta.getNumeroMatricula().equals(numeroMatricula)) {
                actualizado = false;
                return actualizado;
            }
        }
        seleccionada.setNumeroMatricula(numeroMatricula);
        seleccionada.setMarca(marca);
        seleccionada.setModelo(modelo);
        seleccionada.setFechaFabricacion(fechaFabricacion);
        seleccionada.setCapacidadCargaToneladas(capacidadCargaToneladas);
        seleccionada.setTarifaBase(tarifaBase);
        seleccionada.setPorcentaje(porcentaje);
        return actualizado;
    }

    // Método para agregar una cliente verificando que no exista
    public boolean agregarCliente(Cliente cliente) {
        boolean agregado = false;
        if (!clienteExistente(cliente.getTelefono())) {
            clientes.add(cliente);
            agregado = true;
        }
        return agregado;
    }

    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    // Método para actualizar un cliente verificando que no exista una con el mismo
    // numero de telefono
    public boolean actualizarCliente(Cliente seleccionado, String nombre, String telefono) {
        boolean actualizado = true;
        for (Cliente cliente : clientes) {
            if (cliente != seleccionado && cliente.getTelefono().equals(telefono)) {
                actualizado = false;
                return actualizado;
            }
        }
        seleccionado.setTelefono(telefono);
        seleccionado.setNombre(nombre);
        return actualizado;
    }

    public boolean clienteExistente(String telefono) {
        boolean existente = false;
        for (Cliente cliente : clientes) {
            if (cliente.getTelefono().equals(telefono)) {
                existente = true;
                break;
            }
        }
        return existente;
    }

    public boolean agregarReserva(Reserva reserva) {
        boolean agregada = false;
        if(!reservaExistente(reserva.getCodigo())){
            reservas.add(reserva);
            agregada = true;
        }
        return agregada;
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public boolean reservaExistente(String codigo){
        boolean existente = false;
        for(Reserva reserva: reservas){
            if(reserva.getCodigo().equals(codigo)){
                existente = true;
                break;
            }
        }
        return existente;
    }

    public int calcularDias(LocalDate fecha1, LocalDate fecha2){
        return (int) ChronoUnit.DAYS.between(fecha1, fecha2);
    }

    
    public boolean validarFechaPosterior(LocalDate fecha1, LocalDate fecha2){
        return fecha1.isAfter(fecha2) || fecha1.isEqual(fecha2);
    }
}
