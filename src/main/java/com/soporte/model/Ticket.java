package com.soporte.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer numeroTicket;

    private Integer idCliente;

    private Integer legajoPersonaAsignada;

    private Integer idProducto;

    private EstadoTicket estado;

    private Date fechaCreacion;

    public Ticket() {
        this.fechaCreacion = new Date();
    }

    public Ticket(Integer legajoCliente, Integer legajoPersonaAsignada, Integer idProducto, EstadoTicket estado) {
        this.idCliente = legajoCliente;
        this.legajoPersonaAsignada = legajoPersonaAsignada;
        this.idProducto = idProducto;
        this.estado = estado;
        this.fechaCreacion = new Date();
    }

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public Integer getLegajoPersonaAsignada() {
        return this.legajoPersonaAsignada;
    }

    public Integer getIdProducto() {
        return this.idProducto;
    }

    public EstadoTicket getEstado() {
        return this.estado;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public Integer getNumeroTicket() {
        return this.numeroTicket;
    }

    public void setNumeroTicket(Integer numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

}
