package com.soporte.model;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer numeroTicket;

    private Integer idCliente;

    private Integer legajoPersonaAsignada;

    private Integer idProducto;

    private EstadoTicket estado;

    public Ticket() {
    }

    public Ticket(Integer legajoCliente, Integer legajoPersonaAsignada, Integer idProducto, EstadoTicket estado) {
        this.idCliente = legajoCliente;
        this.legajoPersonaAsignada = legajoPersonaAsignada;
        this.idProducto = idProducto;
        this.estado = estado;
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
}
