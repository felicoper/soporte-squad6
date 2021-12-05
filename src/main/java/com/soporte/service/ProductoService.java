package com.soporte.service;

import com.soporte.model.Producto;
import com.soporte.model.VersionProducto;
import com.soporte.repository.ProductoRepository;
import com.soporte.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public VersionProducto buscarVersion(Integer idVersionProductoEnRequest, Integer idProductoEnRequest){
        Collection<Producto> productos = new ArrayList<>();
        productoRepository.findAll().forEach(productos::add);

        ////////////////////////////////

        Optional<Producto> unProducto = productoRepository.findById(idProductoEnRequest);
        if(unProducto.isPresent()) {
            Producto producto = unProducto.get();
            VersionProducto versionProducto = producto.getVersionProducto(idVersionProductoEnRequest);
            if(versionProducto != null) {
                return versionProducto;
            }
        }
        
        // lanzá  una excepcion
        throw new RuntimeException("No se encontró la versión del producto");

    }

    public void saveDatabase(Producto producto) {
        productoRepository.save(producto);
    }


    public void agregarVersionProducto(Producto producto, VersionProducto versionProducto) {
        producto.agregarVersion(versionProducto);
        productoRepository.deleteById(producto.getIdProducto());
        productoRepository.save(producto);
    }


    public void deleteById(Integer id) {
        //empleadoRepository.deleteById(id);
    } 

}
