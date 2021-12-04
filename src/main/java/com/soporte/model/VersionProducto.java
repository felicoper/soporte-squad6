package com.soporte.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

@Entity
public class VersionProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idVersionProducto;
    
    private String versionProducto;
    private Date fechaLanzamiento;    
    private ArrayList<Ticket> tickets;
    
    public VersionProducto(Integer idVersionProducto, String versionProducto, Date fechaLanzamiento) {
        this.idVersionProducto = idVersionProducto;
        this.versionProducto = versionProducto;
        this.fechaLanzamiento = fechaLanzamiento;
        this.tickets = new ArrayList<>();
    }

    public String getVersionProducto() {
        return versionProducto;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void agregarTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void setIdVersionProducto(int idVersionProducto) {
        this.idVersionProducto = idVersionProducto;
    }

    public Integer getIdVersionProducto() {
        return this.idVersionProducto;
    }

}