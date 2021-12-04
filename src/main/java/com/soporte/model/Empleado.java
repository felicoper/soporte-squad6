package com.soporte.model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Empleado {
    
    @Id
    private Integer legajo;

    private String nombre;

    private String apellido;

    public Empleado(Integer legajo, String nombre, String apellido) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Empleado() {

    }

    public Integer getId() {
        return this.legajo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getApellido(){
        return this.apellido;
    }
}