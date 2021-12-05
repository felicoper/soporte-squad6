package com.soporte.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.*;


@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idProducto;

    private String nombreProducto;
    private ArrayList<VersionProducto> versionesProducto;
    
    public Producto(Integer idProducto, String nombreProducto, VersionProducto versionInicial) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.versionesProducto = new ArrayList<>();
        this.versionesProducto.add(versionInicial);
    }

    public Producto() {
    }

    public Integer getIdProducto() {
        return this.idProducto;
    }

    public String getNombre() {
        return this.nombreProducto;
    }

    public ArrayList<VersionProducto> getVersiones() {
        return this.versionesProducto;
    }

    public void agregarVersion(VersionProducto versionProducto) {
        this.versionesProducto.add(versionProducto);
    }

	public VersionProducto getVersionProducto(Integer idVersionProductoEnRequest){
		return versionesProducto
        .stream()
        .filter(versionProducto -> versionProducto.getIdVersionProducto().equals(idVersionProductoEnRequest))
        .findFirst()
        .get();
	}



}


