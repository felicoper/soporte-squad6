package com.soporte.service;

import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.model.Ticket;
import com.soporte.model.VersionProducto;
import com.soporte.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
	private ClientService clienteExternService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ProductoService productoService;

    public Ticket createTicket(Ticket ticketRequest) throws ClienteInvalidoExcepcion {
        VersionProducto versionProd = productoService.buscarVersion(ticketRequest.getIdVersionProducto());

        if(versionProd != null && clienteExternService.findById(ticketRequest.getIdCliente()) != null
            && empleadoService.findById(ticketRequest.getLegajoEmpleado()) != null){
                ticketRepository.save(ticketRequest);
                versionProd.agregarTicket(ticketRequest);
            }
        return ticketRequest;
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
