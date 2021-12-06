package com.soporte.model;

public class TicketRequest{
    private String titulo;
    private String descripcion;
    private Integer idVersionProducto;
    private Integer idCliente;
    private Integer legajoEmpleado;
    protected TipoTicket tipoTicket;

    public TicketRequest(){

    }
    public TicketRequest (String titulo, String descripcion, Integer idCliente, Integer legajoEmpleado, Integer idVersionProducto, TipoTicket tipoTicket) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idCliente = idCliente;
        this.legajoEmpleado = legajoEmpleado;
        this.idVersionProducto = idVersionProducto;
        this.tipoTicket = tipoTicket;
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
}
