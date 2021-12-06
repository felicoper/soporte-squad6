package com.soporte.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "productos")
public class Producto {
    @Id
    private Integer id;

    private String nombre;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<VersionProducto> versionesProducto  = new ArrayList<>();

    public Producto(Integer idProducto, String nombre) {
        this.id = idProducto;
        this.nombre = nombre;
    }

    public Producto() {
        //this.versionesProducto = new ArrayList<>();
    }

    public Integer getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Collection<VersionProducto> getVersionesProducto() {
        return this.versionesProducto;
    }

    public void agregarVersion(VersionProducto versionProducto) {
        this.versionesProducto.add(versionProducto);
    }

	// public Optional<VersionProducto> getVersionProducto(Integer idVersionProductoEnRequest){
	// 	return versionesProducto
    //         .stream()
    //         .filter(versionProducto -> versionProducto.getVersionProducto().equals(idVersionProductoEnRequest))
    //         .findFirst();
	// }



}
