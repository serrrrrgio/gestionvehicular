package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;


public abstract class Vehiculo {
    private SimpleStringProperty numeroMatricula;
    private SimpleStringProperty marca;
    private SimpleStringProperty modelo;
    private SimpleObjectProperty<LocalDate> fechaFabricacion;
    private Reserva reserva;
    private SimpleDoubleProperty tarifaBase;

    public Vehiculo(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion, Reserva reserva, double tarifaBase) {
        this.numeroMatricula = new SimpleStringProperty(numeroMatricula);
        this.marca = new SimpleStringProperty(marca);
        this.modelo = new SimpleStringProperty(modelo);
        this.fechaFabricacion = new SimpleObjectProperty<>(fechaFabricacion);
        this.reserva = reserva;
        this.tarifaBase = new SimpleDoubleProperty(tarifaBase);
    }

    public String getNumeroMatricula() {
        return numeroMatricula.get();
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula.set(numeroMatricula);
    }

    public SimpleStringProperty numeroMatriculaProperty() {
        return numeroMatricula;
    }

    public String getMarca() {
        return marca.get();
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public SimpleStringProperty marcaProperty() {
        return marca;
    }

    public String getModelo() {
        return modelo.get();
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public SimpleStringProperty modeloProperty() {
        return modelo;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion.get();
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion.set(fechaFabricacion);
    }

    public SimpleObjectProperty<LocalDate> fechaFabricacionProperty() {
        return fechaFabricacion;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public abstract double calcularCosto();

    public double getTarifaBase() {
        return tarifaBase.get();
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase.set(tarifaBase);
    }

    public SimpleDoubleProperty tarifaBaseProperty() {
        return tarifaBase;
    }
}

