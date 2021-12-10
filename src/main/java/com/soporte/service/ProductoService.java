package com.soporte.service;

import com.soporte.Exceptions.VersionProductoInexistente;
import com.soporte.Exceptions.VersionProductoSinTicketsExcepcion;
import com.soporte.model.Producto;
import com.soporte.model.Ticket;
import com.soporte.model.VersionProducto;
import com.soporte.repository.ProductoRepository;
import com.soporte.repository.VersionProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VersionProductoRepository versionProductoRepository;

    // public VersionProducto buscarVersion(Integer idVersionProductoEnRequest, Integer idProductoEnRequest) throws VersionProductoInexistente {
    //     Optional<Producto> unProducto = productoRepository.findById(idProductoEnRequest);
    //     if(unProducto.isPresent()) {
    //         Producto producto = unProducto.get();
    //         Optional<VersionProducto> versionProducto = producto.getVersionProducto(idProductoEnRequest);
    //         if(versionProducto.isPresent()) {
    //             return versionProducto.get();
    //         }else{
    //             throw new VersionProductoInexistente("No se encontró la version producto");
    //         }
    //     }else{
    //         throw new VersionProductoInexistente("No se encontró el producto");

    //     }
    //  }

    public void saveDatabase(Producto producto) {
        productoRepository.save(producto);
    }

    public void saveDatabaseVersion(VersionProducto version){
        versionProductoRepository.save(version);
    }

    // public void agregarVersionProducto(VersionProducto versionProducto, Producto producto) {
    //     Producto productoEnBaseDeDatos = productoRepository.findById(versionProducto.getProducto().getId()).get();
    //     versionProducto.setProducto(producto);
    //     versionProductoRepository.save(versionProducto);
    //     productoEnBaseDeDatos.agregarVersion(versionProducto);
    //     productoRepository.save(productoEnBaseDeDatos);
    // }

    public Collection<Producto> getProductos() {
        Collection<Producto> productos = new ArrayList<>();
        productoRepository.findAll().forEach(productos::add);
        return productos;
    }

    public Collection<VersionProducto> getVersionesProductos() {
        Collection<VersionProducto> versiones = new ArrayList<>();
        versionProductoRepository.findAll().forEach(versiones::add);
        return versiones;
    }

    public VersionProducto getVersionProducto(Integer id) throws VersionProductoInexistente{
        if (id < 0){
            throw new VersionProductoInexistente("El id de la version de producto no puede ser negativo");
        }

        Optional<VersionProducto> version = versionProductoRepository.findById(id);
        if(version.isPresent()) {
            return version.get();
        }else{
            throw new VersionProductoInexistente("No se encontró la version producto");
        }
    }

    public Collection<Ticket> getTicketsOfVersion(Integer id_producto) throws VersionProductoSinTicketsExcepcion{
        Optional<VersionProducto> version = versionProductoRepository.findById(id_producto);
        if(version.isPresent()) {
            if (version.get().getTickets().isEmpty()) 
                throw new VersionProductoSinTicketsExcepcion("La version de producto no tiene tickets registrados");
            return version.get().getTickets();
        }else{
            throw new VersionProductoSinTicketsExcepcion("No se encontró la version producto existente");
        }
    }

}
