package com.soporte.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.service.TicketService;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.soporte.service.ProductoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class TicketController {

    @Autowired
	private TicketService ticketService;

	@Autowired
	private ProductoService productService;

    @PostMapping("/tickets")
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket createTicket(@Valid @RequestBody TicketRequest ticket) {
		return ticketService.createTicket(ticket);
	}

	@GetMapping("/tickets")
	public Collection<Ticket> getTickets() {
		return ticketService.getTickets();
	}

	@GetMapping("/tickets/{id_producto}")
	public Collection<Ticket> getTicketsOfVersionProduct(@PathVariable Integer id_producto) {
		return productService.getTicketsOfVersion(id_producto);
	}

	@PatchMapping("/tickets/{id_ticket}")
	public Ticket updateTicket(@PathVariable Integer id_ticket, @RequestBody Map<String, Object> changes)
	{
		return ticketService.updateTicket(id_ticket, changes);
	}

	@DeleteMapping("/tickets/{id}")
	public void deleteTicket(@PathVariable Integer id) {
		ticketService.deleteById(id);
	}

}
