package com.soporte.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketRequest extends Ticket {
    @NotNull(message = "El id del producto es requerido")
    private Integer idProducto;
    
    public TicketRequest() {
    }
    public TicketRequest(String titulo, String descripcion, Integer legajoCliente, Integer legajoEmpleado, Integer idVersionProducto, TipoTicket tipoTicket) {
        super(titulo,  descripcion,  legajoCliente,  legajoEmpleado,  idVersionProducto,  tipoTicket);
    }
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public Ticket parseToTicket() {
        Ticket ticket = new Ticket(this.getTitulo(), this.getDescripcion(), this.getIdCliente(), this.getLegajoEmpleado(), this.getIdVersionProducto(), this.getTipoTicket());
        ticket.setNumeroTicket(numeroTicket);
        return ticket;
    }
    
}
