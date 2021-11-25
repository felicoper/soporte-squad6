package com.soporte.service;

import com.soporte.model.Ticket;
import com.soporte.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Collection<Ticket> getTickets() {
        Collection<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}
