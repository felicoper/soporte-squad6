package com.soporte.model;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Empleado {
    @Id
    @JsonProperty("legajo")
    private Integer legajo;

    @JsonProperty("Nombre")
    private String nombre;

    @JsonProperty("Apellido")
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

}