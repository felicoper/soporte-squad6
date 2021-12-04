package com.soporte.repository;

import com.soporte.model.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmpleadoRepository extends CrudRepository<Empleado, Integer>{ }