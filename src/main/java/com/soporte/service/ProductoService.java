package com.soporte.service;

import com.soporte.Exceptions.VersionProductoInexistente;
import com.soporte.model.Producto;
import com.soporte.model.VersionProducto;
import com.soporte.repository.ProductoRepository;
import com.soporte.repository.TicketRepository;
import com.soporte.repository.VersionProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.PostConstruct;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VersionProductoRepository versionProductoRepository;

    
    
    public VersionProducto buscarVersion(Integer idVersionProductoEnRequest, Integer idProductoEnRequest) throws VersionProductoInexistente {
        Optional<Producto> unProducto = productoRepository.findById(idProductoEnRequest);
        if(unProducto.isPresent()) {
            Producto producto = unProducto.get();
            Optional<VersionProducto> versionProducto = producto.getVersionProducto(idProductoEnRequest);
            if(versionProducto.isPresent()) {
                return versionProducto.get();
            }else{
                throw new VersionProductoInexistente("No se encontró la version producto");
            }
        }else{
            throw new VersionProductoInexistente("No se encontró el producto");

        }
     }

    public void saveDatabase(Producto producto) {
        producto.getVersionesProducto().forEach(versionProducto -> versionProductoRepository.save(versionProducto));
        productoRepository.save(producto);
    }

    public void agregarVersionProducto(VersionProducto versionProducto) {
        Producto productoEnBaseDeDatos = productoRepository.findById(versionProducto.getIdProductoAsociado()).get();
        versionProductoRepository.save(versionProducto);
        productoEnBaseDeDatos.agregarVersion(versionProducto);
        productoRepository.save(productoEnBaseDeDatos);
    }

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

}
