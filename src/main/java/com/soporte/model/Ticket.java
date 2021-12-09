package com.soporte.model;

import javax.persistence.*;

import java.util.Date;
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer numeroTicket;

    private String titulo;
    private String descripcion;
    private EstadoTicket estadoTicket;
    private TipoTicket tipoTicket;
    private Severidad severidadTicket;

    private Date fechaCreacion;
    private Date fechaFinalizacion;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idCliente", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idEmpleado", referencedColumnName = "id")
    private Empleado empleadoAsignado;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idVersionproducto", referencedColumnName = "idVersionProducto")
    private VersionProducto versionProducto;


    public Ticket() {
        this.fechaCreacion = new Date();
        this.estadoTicket = EstadoTicket.ABIERTO;
        this.severidadTicket = Severidad.SIN_SEVERIDAD;
    }

    public Ticket(String titulo, String descripcion, TipoTicket tipoTicket, Severidad severidadTicket) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estadoTicket = EstadoTicket.ABIERTO;
        this.tipoTicket = tipoTicket;
        this.fechaCreacion = new Date();
        this.severidadTicket = severidadTicket;
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

    public Severidad getSeveridadTicket() {
        return this.severidadTicket;
    }

    public void setSeveridadTicket(Severidad severidadTicket) {
        this.severidadTicket = severidadTicket;
    }

    public void setTipoTicket(TipoTicket tipoTicket) {
        this.tipoTicket = tipoTicket;
    }

    public void setEstadoTicket(EstadoTicket estado) {
        this.estadoTicket = estado;
    }

    public void finalizarTicket(){
        this.fechaFinalizacion = new Date();
    }
}
