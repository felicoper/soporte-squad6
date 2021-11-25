package com.soporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soporte.model.Ticket;
import com.soporte.service.TicketService;

import java.util.Collection;
import java.util.Optional;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class SoporteApplication {

	@Autowired
	private TicketService ticketService;

	public static void main(String[] args) {
		SpringApplication.run(SoporteApplication.class, args);
	}

	@PostMapping("/tickets")
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket createTicket(@RequestBody Ticket ticket) {
		return ticketService.createTicket(ticket);
	}

	@GetMapping("/tickets")
	public Collection<Ticket> getTickets() {
		return ticketService.getTickets();
	}

	@PutMapping("/tickets/{id}")
	public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable Long id) {
		Optional<Ticket> ticketOptional = ticketService.findById(id);
		if (!ticketOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		ticketService.save(ticket);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/tickets/{id}")
	public void deleteTicket(@PathVariable Long id) {
		ticketService.deleteById(id);
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}


}
