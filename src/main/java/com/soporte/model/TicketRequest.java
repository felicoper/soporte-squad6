package com.soporte.model;

import javax.validation.constraints.NotNull;

public class TicketRequest{
    @NotNull(message = "El titulo es requerido")
    private String titulo;

    @NotNull(message = "La descripcion es requerida")
    private String descripcion;

    @NotNull(message = "El id cliente es requerido")
    private Integer idCliente;

    @NotNull(message = "El id empleado es requerido")
    private Integer legajoEmpleado;

    @NotNull(message = "El id de version de producto es requerido")
    private Integer idVersionProducto;

    @NotNull(message = "La severidad del ticket es requerido")
    private Severidad serveridadTicket;

    @NotNull(message = "El tipo de ticket es requerido")
    private TipoTicket tipoTicket;

    public TicketRequest(){

    }
    public TicketRequest (String titulo, String descripcion, Integer idCliente, Integer legajoEmpleado, Integer idVersionProducto, TipoTicket tipoTicket, Severidad serveridadTicket) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idCliente = idCliente;
        this.legajoEmpleado = legajoEmpleado;
        this.idVersionProducto = idVersionProducto;
        this.tipoTicket = tipoTicket;
        this.serveridadTicket = serveridadTicket;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public Integer getIdVersionProducto() {
        return idVersionProducto;
    }
    public Integer getIdCliente() {
        return idCliente;
    }
    public Integer getLegajoEmpleado() {
        return legajoEmpleado;
    }
    public TipoTicket getTipoTicket() {
        return tipoTicket;
    }
    public Severidad getServeridadTicket() {
        return serveridadTicket;
    }
}
