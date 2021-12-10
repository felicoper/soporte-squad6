package com.soporte.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Empleado {

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
