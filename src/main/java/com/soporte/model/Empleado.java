package com.soporte.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue
    private Integer id;

    @JsonProperty("legajo")
    private Integer legajo;

    @JsonProperty("Nombre")
    private String nombre;

    @JsonProperty("Apellido")
    private String apellido;

    @JsonIgnore
    @OneToMany(mappedBy = "empleadoAsignado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

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
