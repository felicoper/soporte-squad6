package com.soporte.model;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long legajoCliente;
    private Long legajoPersonaAsignada;
    private Long idProducto;
    private String estado;

    public Ticket(){ }

    public Ticket(Long legajoCliente, Long legajoPersonaAsignada, Long idProducto, String estado) {
        this.legajoCliente = legajoCliente;
        this.legajoPersonaAsignada = legajoPersonaAsignada;
        this.idProducto = idProducto;
        this.estado = estado;
    }

    public void cambiarEstado(String nuevoEstado){
        if(this.estado != "cerrado") {
            this.estado = nuevoEstado;
        }
    }

    public Long getId(){
        return this.id;
    }
    public Long getLegajoCliente() {
        return this.legajoCliente;
    }
    public Long getLegajoPersonaAsignada(){
        return this.legajoPersonaAsignada;
    }
    public Long getIdProducto() {
        return this.idProducto;
    }
    public String getEstado() {
        return this.estado;
    }
}
