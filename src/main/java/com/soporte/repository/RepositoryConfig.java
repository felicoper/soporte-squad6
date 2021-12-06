package com.soporte.repository;

import com.soporte.model.Cliente;
import com.soporte.model.Empleado;
import com.soporte.model.Producto;
import com.soporte.model.Ticket;
import com.soporte.model.VersionProducto;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Ticket.class, Cliente.class, Empleado.class, Producto.class, VersionProducto.class);
    }
}
