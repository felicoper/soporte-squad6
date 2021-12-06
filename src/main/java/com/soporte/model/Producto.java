package com.soporte.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
public class Producto {
    @Id
    private Integer id;

    private String nombreProducto;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<VersionProducto> versionesProducto;
    
    public Producto(Integer idProducto, String nombreProducto, VersionProducto versionInicial) {
        this.id = idProducto;
        this.nombreProducto = nombreProducto;
        this.versionesProducto = new ArrayList<>();
        this.agregarVersion(versionInicial);
        versionInicial.setIdVersionProducto(idProducto);
    }
    
    public Producto() {
        this.versionesProducto = new ArrayList<>();
    }

    public Integer getId() {
        return this.id;
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public Collection<VersionProducto> getVersionesProducto() {
        System.out.println(this.versionesProducto);
        return this.versionesProducto;
    }

  
    public void agregarVersion(VersionProducto versionProducto) {
        this.versionesProducto.add(versionProducto);
    }

	public Optional<VersionProducto> getVersionProducto(Integer idVersionProductoEnRequest){
		return versionesProducto
            .stream()
            .filter(versionProducto -> versionProducto.getIdVersionProducto().equals(idVersionProductoEnRequest))
            .findFirst();
	}



}


