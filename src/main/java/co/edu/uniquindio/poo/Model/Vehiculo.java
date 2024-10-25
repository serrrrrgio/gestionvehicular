package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;

public abstract class Vehiculo {
    public String numeroMatricula;
    public String marca;
    public String modelo;
    public LocalDate fechaFabricacion;
    
    public Vehiculo(String numeroMatricula, String marca, String modelo, LocalDate fechaFabricacion) {
        this.numeroMatricula = numeroMatricula;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaFabricacion = fechaFabricacion;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }


    public abstract void calcularCosto();


}
