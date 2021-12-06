package com.soporte.model;

import java.util.ArrayList;
import java.util.Date;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class VersionProducto {
    @Id
    private Integer id;
    
    private Integer idProductoAsociado;
    private String versionProducto;
    private Date fechaLanzamiento;    

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Ticket> tickets;

    public VersionProducto(Integer idVersionProducto, Integer idProductoAsociado, String versionProducto, Date fechaLanzamiento) {
        this.id = idVersionProducto;
        this.idProductoAsociado = idProductoAsociado;
        this.versionProducto = versionProducto;
        this.fechaLanzamiento = fechaLanzamiento;
        this.tickets = (Collection<Ticket>) new ArrayList<Ticket>();
    }

    public VersionProducto() {
        this.tickets = (Collection<Ticket>) new ArrayList<Ticket>();
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
        this.id = idVersionProducto;
    }

    public Integer getIdVersionProducto() {
        return this.id;
    }

    public Integer getIdProductoAsociado() {
        return this.idProductoAsociado;
    }

    public void setIdProductoAsociado(Integer idProductoAsociado) {
        this.idProductoAsociado = idProductoAsociado;
    }

}