package com.soporte.service;

import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.model.EstadoTicket;
import com.soporte.model.Ticket;
import com.soporte.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket, ClientExternService clienteService) throws ClienteInvalidoExcepcion {
        if(clienteService.findById(ticket.getIdCliente()) != null)
            ticketRepository.save(ticket);
            
        return ticket;
    }

    public Collection<Ticket> getTickets() {
        Collection<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }

    public Optional<Ticket> findById(Integer id) {
        return ticketRepository.findById(id);
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void deleteById(Integer id) {
        ticketRepository.deleteById(id);
    }
}
