package com.soporte.model;

import java.util.ArrayList;
import java.util.Date;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VersionProducto {

    @Id
    private Integer idVersionProducto;

    private String versionProducto;
    private Date fechaLanzamiento;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    protected Producto producto;

    @JsonIgnore
    @OneToMany(mappedBy = "versionProducto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets  = new ArrayList<>();

    public VersionProducto(Integer idVersionProducto, String versionProducto, Date fechaLanzamiento) {
        this.idVersionProducto = idVersionProducto;
        //this.producto = producto;
        this.versionProducto = versionProducto;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public VersionProducto() {
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public String getVersionProducto() {
        return versionProducto;
    }

    //get tickets
    public Collection<Ticket> getTickets() {
        return tickets;
    }
    public void agregarTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void setIdVersionProducto(int idVersionProducto) {
        this.idVersionProducto = idVersionProducto;
    }

    public Integer getId() {
        return this.idVersionProducto;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
