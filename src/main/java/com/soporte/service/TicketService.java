package com.soporte.service;

import com.soporte.Exceptions.CambioEstadoTicketCerradoExcepcion;
import com.soporte.Exceptions.CamposFaltantesTicketExcepcion;
import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.Exceptions.EmpleadoInvalidoExcepcion;
import com.soporte.Exceptions.TicketInexistenteExcepcion;
import com.soporte.Exceptions.VersionProductoInexistente;
import com.soporte.model.Cliente;
import com.soporte.model.Empleado;
import com.soporte.model.EstadoTicket;
import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;
import com.soporte.model.VersionProducto;
import com.soporte.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

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

    @Transactional
    public Ticket createTicket(@Valid TicketRequest ticketRequest) throws CamposFaltantesTicketExcepcion, VersionProductoInexistente,ClienteInvalidoExcepcion,EmpleadoInvalidoExcepcion {
        VersionProducto versionProducto = productoService.getVersionProducto(ticketRequest.getIdVersionProducto());
        Cliente cliente = clienteExternService.findById(ticketRequest.getIdCliente());
        Empleado empleado = empleadoService.findById(ticketRequest.getLegajoEmpleado());

        Ticket ticketCreado = new Ticket(ticketRequest.getTitulo(), ticketRequest.getDescripcion(), ticketRequest.getIdCliente(), ticketRequest.getLegajoEmpleado(), ticketRequest.getTipoTicket(), ticketRequest.getSeveridadTicket());

        ticketCreado.setVersionProducto(versionProducto);
        ticketRepository.save(ticketCreado);

        return ticketCreado;
    }

    public Collection<Ticket> getTickets() {
        Collection<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }


    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public Ticket getTicketById( Integer id_ticket) throws TicketInexistenteExcepcion {
        Ticket ticket = ticketRepository.findById(id_ticket).orElseThrow(() -> new TicketInexistenteExcepcion("No existe el ticket con id " + id_ticket));
        return ticket;
    }

    public Ticket updateTicket(Integer id_ticket, Map<String, Object> changes) throws TicketInexistenteExcepcion,VersionProductoInexistente,ClienteInvalidoExcepcion,EmpleadoInvalidoExcepcion, CambioEstadoTicketCerradoExcepcion{
        Ticket ticket = ticketRepository.findById(id_ticket).orElseThrow(() -> new TicketInexistenteExcepcion("No existe el ticket con id " + id_ticket));

        if(ticket.getEstadoTicket() == EstadoTicket.CERRADO) throw new CambioEstadoTicketCerradoExcepcion("El ticket ya está cerrado");

        changes.forEach( (change, value) -> {
            switch (change) {
                case "titulo": ticket.setTitulo((String) value); break;
                case "descripcion": ticket.setDescripcion((String) value); break;
                case "estado": cambioEstadoTicket(ticket, EstadoTicket.valueOf(value.toString())); break;
                case "severidadTicket": ticket.setSeveridadTicket((Severidad) Severidad.valueOf(value.toString())); break;
                case "tipoTicket": ticket.setTipoTicket((TipoTicket) TipoTicket.valueOf(value.toString())); break;
                case "versionProducto": {
                    VersionProducto versionProducto = productoService.getVersionProducto((Integer) value);
                    ticket.setVersionProducto(versionProducto);
                    break;
                }
                case "idCliente": {
                    Cliente cliente = clienteExternService.findById((Integer) value);
                    ticket.setIdCliente((Integer) value);
                    break;
                }
                case "legajoEmpleado": {
                    Empleado empleado = empleadoService.findById((Integer) value);
                    ticket.setLegajoEmpleado((Integer) value);
                    break;
                }
            }
        });

        ticketRepository.save(ticket);
        return ticket;
    }

    public void deleteById(Integer id) {
        ticketRepository.deleteById(id);
    }

    private void cambioEstadoTicket(Ticket ticket, EstadoTicket estado) {
        ticket.setEstadoTicket(estado);
        if (estado == EstadoTicket.CERRADO) ticket.finalizarTicket();

    }
}
