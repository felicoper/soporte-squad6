package com.soporte.service;

import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.model.Cliente;
import com.soporte.model.Ticket;
import com.soporte.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
	private ClientExternService clienteExternService;

    public Ticket createTicket(Ticket ticket, ClientExternService eeeh) throws ClienteInvalidoExcepcion {
        if(eeeh.findById(ticket.getIdCliente()) != null) {
            ticketRepository.save(ticket);
            return ticket;
        }else{
            throw new ClienteInvalidoExcepcion("El cliente no pertenece a la empresa.");
        }

    }

    public Collection<Ticket> getTickets() {
        Collection<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }

    public Collection<Cliente> getClients() {
        return this.clienteExternService.getClients();
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
