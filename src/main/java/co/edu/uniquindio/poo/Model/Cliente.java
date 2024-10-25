package co.edu.uniquindio.poo.Model;

public class Cliente {
    private String nombre;
    private String telfono;


    public Cliente(String nombre, String telfono) {
        this.nombre = nombre;
        this.telfono = telfono;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getTelfono() {
        return telfono;
    }


    public void setTelfono(String telfono) {
        this.telfono = telfono;
    }

    

    
}
