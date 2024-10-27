package co.edu.uniquindio.poo.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class Moto {
    private SimpleStringProperty matricula;
    private SimpleStringProperty marca;
    private SimpleStringProperty modelo;
    private SimpleDoubleProperty tarifaBase;
    private SimpleObjectProperty<LocalDate> fechaFabricacion;
    private SimpleObjectProperty<TipoCaja> tipoCaja;

    // Constructor de Moto ajustado a la firma solicitada
    public Moto(String matricula, String marca, String modelo, LocalDate fechaFabricacion, double tarifaBase, TipoCaja tipoCaja) {
        this.matricula = new SimpleStringProperty(matricula);
        this.marca = new SimpleStringProperty(marca);
        this.modelo = new SimpleStringProperty(modelo);
        this.fechaFabricacion = new SimpleObjectProperty<>(fechaFabricacion);
        this.tarifaBase = new SimpleDoubleProperty(tarifaBase);
        this.tipoCaja = new SimpleObjectProperty<>(tipoCaja);
    }

    // Método para calcular el costo total de la moto (aquí asumimos que tarifaAdicional es un valor constante o se calcula de otra forma)
    public double calcularCosto() {
        return tarifaBase.get(); // Si necesitas agregar una tarifa adicional, puedes incluirla aquí
    }

    // Métodos getter y setter de propiedad
    public String getMatricula() {
        return matricula.get();
    }
    public void setMatricula(String matricula) {
        this.matricula.set(matricula);
    }
    public SimpleStringProperty matriculaProperty() {
        return matricula;
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

    public double getTarifaBase() {
        return tarifaBase.get();
    }
    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase.set(tarifaBase);
    }
    public SimpleDoubleProperty tarifaBaseProperty() {
        return tarifaBase;
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

    public TipoCaja getTipoCaja() {
        return tipoCaja.get();
    }
    public void setTipoCaja(TipoCaja tipoCaja) {
        this.tipoCaja.set(tipoCaja);
    }
    public SimpleObjectProperty<TipoCaja> tipoCajaProperty() {
        return tipoCaja;
    }
}
