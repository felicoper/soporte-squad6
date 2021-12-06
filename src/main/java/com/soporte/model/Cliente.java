package com.soporte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue
    private Integer id;

    @JsonProperty("id")
    private Integer idCliente;

    @JsonProperty("razon social")
    private String razon_social;

    @JsonProperty("CUIT")
    private String CUIT;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    public Cliente(Integer id, String razon_social, String cuit) {
        this.idCliente = id;
        this.razon_social = razon_social;
        this.CUIT = cuit;
    }

    public Cliente() {

    }

    public Integer getId() {
        return this.idCliente;
    }
}
