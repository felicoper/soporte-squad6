package com.soporte.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {
    @Id
    private Integer id;

    @JsonProperty("razon social")
    private String razon_social;

    @JsonProperty("CUIT")
    private String CUIT;

    public Cliente(Integer id, String razon_social, String cuit) {
        this.id = id;
        this.razon_social = razon_social;
        this.CUIT = cuit;
    }

    public Cliente() {

    }

    public Integer getId() {
        return this.id;
    }
}
