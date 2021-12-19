package com.soporte.service;

import com.soporte.Exceptions.CambioEstadoTicketCerradoExcepcion;
import com.soporte.Exceptions.CampoVacioException;
import com.soporte.Exceptions.CamposFaltantesTicketExcepcion;
import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.Exceptions.EmpleadoInvalidoExcepcion;
import com.soporte.Exceptions.TicketCerradoExcepcion;
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
        Cliente cliente = clienteExternService.findById(ticketRequest.getIdCliente());
        VersionProducto versionProducto = productoService.getVersionProducto(ticketRequest.getIdVersionProducto());

        Empleado empleado = null;
        if(ticketRequest.getLegajoEmpleado() != null){
            empleado = empleadoService.findById(ticketRequest.getLegajoEmpleado());
        }

        Ticket ticketCreado = new Ticket(ticketRequest.getTitulo(), ticketRequest.getDescripcion(), cliente.getId(), empleado != null ? empleado.getId() : null , ticketRequest.getTipoTicket(), ticketRequest.getSeveridadTicket());

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
                case "titulo":{
                    if(value.equals("")|| value == null) throw new CampoVacioException("El campo obligatorio esta vacío");
                    ticket.setTitulo((String) value);
                    break;
                }
                case "descripcion":{
                    if(value.equals("") || value == null) throw new CampoVacioException("El campo obligatorio esta vacío");
                    ticket.setDescripcion((String) value);
                    break;
                }
                case "tarea": ticket.addTarea((String) value); break; // TODO!! call SERVICE.......... THIS.ADDTAREA... EXCEPTION....
                case "estado": cambioEstadoTicket(ticket, EstadoTicket.valueOf(value.toString())); break;
                case "severidadTicket": ticket.setSeveridadTicket((Severidad) Severidad.valueOf(value.toString())); break;
                case "tipoTicket": ticket.setTipoTicket((TipoTicket) TipoTicket.valueOf(value.toString())); break;
                case "legajoEmpleado": {
                    if (empleadoService.findById((Integer) value) != null); ticket.setLegajoEmpleado((Integer) value);
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
        if (estado == EstadoTicket.CERRADO)
            ticket.finalizarTicket();
        else
            ticket.setEstadoTicket(estado);

    }

    public void finalizarTicket(Integer numeroTicket) throws TicketCerradoExcepcion {
        Ticket ticket = ticketRepository.findById(numeroTicket).orElseThrow(() -> new TicketInexistenteExcepcion("No existe el ticket con id " + numeroTicket));

        if (ticket.getEstadoTicket() == EstadoTicket.CERRADO) throw new TicketCerradoExcepcion("El ticket ya está cerrado");

        ticket.finalizarTicket();
        ticketRepository.save(ticket);
    }
}
