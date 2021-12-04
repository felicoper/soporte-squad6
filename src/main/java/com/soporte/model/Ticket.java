package com.soporte.model;

import javax.persistence.*;
import java.util.Date;
// | ID | ID_CLIENTE | LEGAJO_PERSONA_ASIGNADA | ID_PRODUCTO | FECHA_CREACION | FECHA_MODIFICACION | ESTADO
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer numeroTicket;

    private String titulo;

    private String descripcion;
    
    private Integer idCliente;

    private Integer legajoEmpleado;

    private Integer idVersionProducto;

    private EstadoTicket estadoTicket;

    private TipoTicket tipoTicket;

    private Date fechaCreacion;

    private Date fechaFinalizacion;


    public Ticket() {
        this.fechaCreacion = new Date();
    }

    public Ticket(String titulo, String descripcion, Integer legajoCliente, Integer legajoEmpleado, Integer idVersionProducto, TipoTicket tipoTicket) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idCliente = legajoCliente;
        this.legajoEmpleado = legajoEmpleado;
        this.idVersionProducto = idVersionProducto;
        this.estadoTicket = EstadoTicket.ABIERTO;
        this.tipoTicket = tipoTicket;
        this.fechaCreacion = new Date();
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

    public EstadoTicket getEstado() {
        return this.estadoTicket;
    }

    public TipoTicket getTipoTicket() {
        return this.tipoTicket;
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
