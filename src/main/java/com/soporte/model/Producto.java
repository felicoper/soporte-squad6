package com.soporte.model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.*;


@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer numeroProducto;
    private String nombreProducto;
    private HashMap<Integer, VersionProducto> versionesProducto;
    
    public Producto(Integer numeroProducto, String nombreProducto, VersionProducto versionInicial) {
        this.numeroProducto = numeroProducto;
        this.nombreProducto = nombreProducto;
        this.versionesProducto = new HashMap<>();
        this.versionesProducto.put(versionInicial.getIdVersionProducto(), versionInicial);
    }

    public Integer getNumero() {
        return this.numeroProducto;
    }

    public String getNombre() {
        return this.nombreProducto;
    }

    public void agregarVersion(VersionProducto versionProducto) {
        this.versionesProducto.put(versionProducto.getIdVersionProducto(), versionProducto);
    }

}


