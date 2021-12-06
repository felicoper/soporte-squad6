package com.soporte.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


import java.util.Date;
// | ID | ID_CLIENTE | LEGAJO_PERSONA_ASIGNADA |FECHA_CREACION | FECHA_MODIFICACION | ESTADO
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    protected Integer numeroTicket;

    @NotNull(message = "El titulo es requerido")
    protected String titulo;

    @NotNull(message = "La descripcion es requerida")
    protected String descripcion;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idCliente", referencedColumnName = "id")
    @NotNull(message = "El cliente es requerido")
    protected Cliente cliente;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idEmpleado", referencedColumnName = "id")
    @NotNull(message = "El empleado es requerido")
    protected Empleado empleadoAsignado;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idVersionproducto", referencedColumnName = "idVersionProducto")
    @NotNull(message = "El id de version de producto es requerido")
    protected VersionProducto versionProducto;

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

    public Ticket(String titulo, String descripcion, TipoTicket tipoTicket) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estadoTicket = EstadoTicket.ABIERTO;
        this.tipoTicket = tipoTicket;
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

    public Cliente getCliente() {
        return this.cliente;
    }

    public Empleado getEmpleadoAsignado() {
        return this.empleadoAsignado;
    }

    public VersionProducto getVersionProducto() {
        return this.versionProducto;
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

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public void setEmpleadoAsignado(Empleado empleado){
        this.empleadoAsignado = empleado;
    }
    public void setVersionProducto(VersionProducto version){
        this.versionProducto = version;
    }
}
