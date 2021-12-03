package com.soporte.repository;

import com.soporte.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends CrudRepository<Cliente, Integer>{ }
