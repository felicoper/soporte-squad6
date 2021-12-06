package com.soporte.repository;

import com.soporte.model.VersionProducto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VersionProductoRepository extends CrudRepository<VersionProducto, Integer>{ }
