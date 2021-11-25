package com.soporte.model;

import com.soporte.Exceptions.CambioEstadoTicketCerradoExcepcion;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long legajoCliente;
    private Long legajoPersonaAsignada;
    private Long idProducto;
    public Severidad severidad;
    public Estado estado;

    Cliente cliente;
    PersonaAsignada administrador;
    Producto producto;


    public Ticket(Cliente cliente, PersonaAsignada adminstrador, Long idProducto, Estado estado, String titulo, String descripcion, Severidad severidad, Producto producto) {
        this.cliente = cliente;
        this.administrador = adminstrador;
        this.idProducto = idProducto;
        this.estado = estado;
        this.producto = producto;
        this.severidad = severidad;
        if (titulo.isEmpty() || descripcion.isEmpty()) {

        }
    }

    public Ticket() {

    }

    public void cambiarEstado(Estado estadoNuevo) {
        if (this.estado != Estado.CERRADO) {
            this.estado = estadoNuevo;
        }else{
            throw new CambioEstadoTicketCerradoExcepcion("No se puede cambiar el estado a un ticket cerrado");
        }
    }

    public Long getId(){
        return this.id;
    }
    public Long getLegajoCliente() {
        return this.legajoCliente;
    }
    public Long getLegajoPersonaAsignada(){
        return this.legajoPersonaAsignada;
    }
    public Long getIdProducto() {
        return this.idProducto;
    }
    public Estado getEstado() { return this.estado; }

    public void cambiarSeveridad(Severidad severidadNueva) {
        this.severidad = severidadNueva;

    }
}
