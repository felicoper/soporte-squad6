package com.soporte.repository;

import com.soporte.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductoRepository extends CrudRepository<Producto, Integer>{ }
