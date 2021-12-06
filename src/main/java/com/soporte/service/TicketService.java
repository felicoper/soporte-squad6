package com.soporte.service;

import com.soporte.Exceptions.CamposFaltantesTicketExcepcion;
import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.Exceptions.EmpleadoInvalidoExcepcion;
import com.soporte.Exceptions.VersionProductoInexistente;
import com.soporte.model.Cliente;
import com.soporte.model.Empleado;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.VersionProducto;
import com.soporte.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.transaction.Transactional;

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
    public Ticket createTicket(TicketRequest ticketRequest) throws CamposFaltantesTicketExcepcion, VersionProductoInexistente,ClienteInvalidoExcepcion,EmpleadoInvalidoExcepcion {
        if (ticketRequest == null){
            throw new CamposFaltantesTicketExcepcion("El ticket no puede ser nulo");
        }

        if (ticketRequest.getTitulo().isEmpty() || ticketRequest.getDescripcion().isEmpty()) {
            throw new CamposFaltantesTicketExcepcion("Titulo y descripcion son obligatorios");
        }

        //hay que usar try catch??
        VersionProducto versionProd = productoService.getVersionProducto(ticketRequest.getIdVersionProducto());
        if(versionProd == null){
            System.out.println("No existe vp");
            return null;
        }

        Cliente cliente = clienteExternService.findById(ticketRequest.getIdCliente());
        if(cliente == null){
            System.out.println("No existe cliente");
            return null;
        }

        Empleado empleado = empleadoService.findById(ticketRequest.getLegajoEmpleado());
        if(empleado == null){
            System.out.println("No existe empleado");
            return null;
        }

        Ticket ticket = new Ticket(ticketRequest.getTitulo(), ticketRequest.getDescripcion(), ticketRequest.getTipoTicket());
        ticket.setCliente(cliente);
        ticket.setEmpleadoAsignado(empleado);
        ticket.setVersionProducto(versionProd);

        ticketRepository.save(ticket);

        try {
            empleadoService.saveDatabase(empleado);
        } catch (Exception EntityExistsException) {
            System.out.println("El empleado ya existe");
            //update???
        }

        try {
            clienteExternService.saveDatabase(cliente);
        } catch (Exception EntityExistsException) {
            System.out.println("El cliente ya existe");
            //update???
        }



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
