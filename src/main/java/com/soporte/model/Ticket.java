package com.soporte.model;

import javax.persistence.*;

import java.util.Set;
import java.util.Date;
import java.util.HashSet;

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

    // Referencias a los ids
    private Integer idCliente;
    private Integer legajoEmpleado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idVersionproducto", referencedColumnName = "idVersionProducto")
    private VersionProducto versionProducto;

    @ElementCollection
    @Column(name="idTareas")
    private Set<String> idTareas = new HashSet<>();

    public Ticket() {
        this.fechaCreacion = new Date();
        this.estadoTicket = EstadoTicket.ABIERTO;
        this.severidadTicket = Severidad.SIN_SEVERIDAD;
    }

    public Ticket(String titulo, String descripcion, Integer idCliente, Integer legajoEmpleado, TipoTicket tipoTicket,
            Severidad severidadTicket) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idCliente = idCliente;
        this.legajoEmpleado = legajoEmpleado;
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

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public Integer getLegajoEmpleado() {
        return this.legajoEmpleado;
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

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setLegajoEmpleado(Integer legajoEmpleado) {
        this.legajoEmpleado = legajoEmpleado;
    }

    public void setVersionProducto(VersionProducto version) {
        this.versionProducto = version;
    }

    public void addTarea(String idTarea) {
        this.idTareas.add(idTarea);
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
        this.setEstadoTicket(EstadoTicket.CERRADO);
        this.setFechaCierre();
    }

    public Date getFechaCierre() {
        return this.fechaFinalizacion;
    }

    public void setFechaCierre() {
        this.fechaFinalizacion = new Date();
    }

    public Set<String> getIdTareas() {
        return this.idTareas;
    }
}
