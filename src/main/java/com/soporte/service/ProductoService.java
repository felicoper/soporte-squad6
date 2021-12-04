package com.soporte.service;

import com.soporte.model.Producto;
import com.soporte.model.VersionProducto;
import com.soporte.repository.ProductoRepository;
import com.soporte.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository ticketRepository;

    public VersionProducto buscarVersion(Integer idVersionProductoEnRequest) {
        Collection<Producto> productos = new ArrayList<>();
        ticketRepository.findAll().forEach(productos::add);
        ////////////////////////////////
        
        // TODO: mañana revisar esto!!!!!!!! evitar 2ble For!! 
        // TODO: buscar la forma de guardar
       /*
        for (Producto producto : productos) {
            for (VersionProducto versionProducto : producto1.getVersiones()) {
                if (versionProducto.getIdVersionProducto() == idVersionProductoEnRequest) {
                    return versionProducto;
                }
            }
        }
        */
   
    }

    public void crearProducto(Producto producto) {
        ticketRepository.save(producto);
    }






    public void agregarVersionProducto(Producto producto, VersionProducto versionProducto) {
    }
        /*  Collection<Empleado> empleados = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleados::add);
        return empleados;*/
    }


    public void deleteById(Integer id) {
        //empleadoRepository.deleteById(id);
    } 

}
