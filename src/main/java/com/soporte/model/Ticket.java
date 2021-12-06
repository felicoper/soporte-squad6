package com.soporte.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Date;
// | ID | ID_CLIENTE | LEGAJO_PERSONA_ASIGNADA |FECHA_CREACION | FECHA_MODIFICACION | ESTADO
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Integer numeroTicket;

    @NotNull(message = "El titulo es requerido")
    protected String titulo;

    @NotNull(message = "La descripcion es requerida")
    protected String descripcion;
    
    @NotNull(message = "El id cliente es requerido")
    protected Integer idCliente;

    @NotNull(message = "El legajo del empleado es requerido")
    protected Integer legajoEmpleado;

    @NotNull(message = "El id de version de producto es requerido")
    protected Integer idVersionProducto;

    @NotNull(message = "El estado del ticket es requerido")
    protected EstadoTicket estadoTicket;

    @NotNull(message = "El tipo de ticket es requerido")
    protected TipoTicket tipoTicket;
    
    @NotNull(message = "La fecha de creacion es requerida")
    protected Date fechaCreacion;

    protected Date fechaFinalizacion;


    public Ticket() {
        this.fechaCreacion = new Date();
        this.estadoTicket = EstadoTicket.ABIERTO;
    }

    public Ticket(String titulo, String descripcion, Integer legajoCliente, Integer legajoEmpleado, Integer idVersionProducto, TipoTicket tipoTicket) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idCliente = legajoCliente;
        this.legajoEmpleado = legajoEmpleado;
        this.idVersionProducto = idVersionProducto;
        this.tipoTicket = tipoTicket;
        this.estadoTicket = EstadoTicket.ABIERTO;
        this.fechaCreacion = new Date();
    }

    public Integer getNumeroTicket() {
        return numeroTicket;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public Integer getLegajoEmpleado() {
        return this.legajoEmpleado;
    }

    public Integer getIdVersionProducto() {
        return this.idVersionProducto;
    }

    public EstadoTicket getEstadoTicket() {
        return this.estadoTicket;
    }

    public TipoTicket getTipoTicket() {
        return this.tipoTicket;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }


    public void setNumeroTicket(Integer numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public Date getFechaFinalizacion() {
        return this.fechaFinalizacion;
    }

    public String setTitulo(String titulo) {
        return this.titulo = titulo;
    }

    public String setDescripcion(String descripcion) {
        return this.descripcion = descripcion;
    }
}
