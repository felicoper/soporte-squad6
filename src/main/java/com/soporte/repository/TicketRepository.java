package com.soporte.repository;

import com.soporte.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketRepository extends CrudRepository<Ticket, Long>{ }
