package com.soporte.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketRequest extends Ticket {
    private Integer idProducto;
    
    public TicketRequest(String titulo, String descripcion, Integer legajoCliente, Integer legajoEmpleado, Integer idVersionProducto, TipoTicket tipoTicket) {
        super(titulo,  descripcion,  legajoCliente,  legajoEmpleado,  idVersionProducto,  tipoTicket);
    }
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}
