package com.soporte.service;

import com.soporte.Exceptions.CamposFaltantesTicketExcepcion;
import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.Exceptions.EmpleadoInvalidoExcepcion;
import com.soporte.Exceptions.VersionProductoInexistente;
import com.soporte.model.Cliente;
import com.soporte.model.Empleado;
import com.soporte.model.EstadoTicket;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.VersionProducto;
import com.soporte.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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

    public Ticket createTicket(TicketRequest ticketRequest) throws CamposFaltantesTicketExcepcion, VersionProductoInexistente,ClienteInvalidoExcepcion,EmpleadoInvalidoExcepcion {
        if (ticketRequest == null){
            throw new CamposFaltantesTicketExcepcion("El ticket no puede ser nulo");
        }
        
        if (ticketRequest.getTitulo().isEmpty() || ticketRequest.getDescripcion().isEmpty()) {
            throw new CamposFaltantesTicketExcepcion("Titulo y descripcion son obligatorios");
        }

        if (ticketRequest.getEstadoTicket() == EstadoTicket.CERRADO) {
            throw new CamposFaltantesTicketExcepcion("No se puede crear ticket cerrado");
        }

        if(ticketRequest.getFechaFinalizacion() != null){
            throw new CamposFaltantesTicketExcepcion("No se puede crear ticket con fecha de finalizacion");
        }

        VersionProducto versionProducto = productoService.buscarVersion(ticketRequest.getIdVersionProducto(), ticketRequest.getIdProducto());
        boolean existe_version_producto = (versionProducto != null);
        boolean existe_cliente = (clienteExternService.findById(ticketRequest.getIdCliente()) != null);
        boolean existe_empleado = (empleadoService.findById(ticketRequest.getLegajoEmpleado()) != null);

        if(existe_version_producto && existe_cliente && existe_empleado){
            Ticket ticketCreado = ticketRequest.parseToTicket();
            versionProducto.agregarTicket(ticketCreado);
            ticketRepository.save(ticketCreado);
            return ticketCreado;
        }
        return null;
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
