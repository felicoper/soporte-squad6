package com.soporte;

import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.model.Cliente;
import com.soporte.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.VersionProducto;
import com.soporte.service.TicketService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.soporte.model.Empleado;
import com.soporte.model.Producto;
import com.soporte.service.EmpleadoService;
import com.soporte.service.ProductoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class SoporteApplication {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private ClientService clienteExternService;

	@Autowired
	private EmpleadoService empleadoService;


	@Autowired
	private ProductoService productService;

	public static void main(String[] args) {
		SpringApplication.run(SoporteApplication.class, args);
	}


	/// TICKETS 
	@PostMapping("/tickets")
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket createTicket(@Valid @RequestBody TicketRequest ticket) {
		return ticketService.createTicket(ticket);
	}

	@GetMapping("/tickets")
	public Collection<Ticket> getTickets() {
		return ticketService.getTickets();
	}

	////////// https://nullbeans.com/using-put-vs-patch-when-building-a-rest-api-in-spring/
	/*@PatchMapping("/tickets/{id}")
	public ResponseEntity<Ticket> updateTicket(@RequestBody TicketRequest ticket, @PathVariable Integer id) {
		Optional<Ticket> ticketOptional = ticketService.findById(id);
		if (!ticketOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		ticketService.save(ticket);
		return ResponseEntity.noContent().build();
	}*/


	@DeleteMapping("/tickets/{id}")
	public void deleteTicket(@PathVariable Integer id) {
		ticketService.deleteById(id);
	}

	/// CLIENTES

	@GetMapping("/clientes")
	public Collection<Cliente> getClientes(){
		return clienteExternService.getClientsExterns();
	}

	@GetMapping("/clientes/{id}")
	public Cliente getClientByID(@PathVariable("id") int id){
        return clienteExternService.findById(id);
	}

	/// EMPLEADOS

	@GetMapping("/empleados")
	public Collection<Empleado> getEmpleados(){
		return empleadoService.getEmpleados();
	}

	@GetMapping("/empleados/{id}")
	public Empleado getEmpleadosByID(@PathVariable("id") int id){
        return empleadoService.findById(id);
	}


	/// PRODUCOS Y VERSIONES PRODUCTOS

	@GetMapping("/productos")
	public Collection<Producto> getProducto(){
		return productService.getProductos();
	}

	@GetMapping("/versiones-productos")
	public Collection<VersionProducto> getVersionesProducto(){
		return productService.getVersionesProductos();
	}
	
	@PostConstruct
	public void init() throws ParseException {
		Producto producto1 = new Producto(1, "SIU Guarani", new VersionProducto(1, 1, "0.99b", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2018")));
        Producto producto2 = new Producto(2, "Linux", new VersionProducto(2, 2, "3.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2018")));
        productService.saveDatabase(producto1);
        productService.saveDatabase(producto2);

        productService.agregarVersionProducto(new VersionProducto(3, 1, "1.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019")));
        productService.agregarVersionProducto(new VersionProducto(4, 1, "2.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2020")));
        productService.agregarVersionProducto(new VersionProducto(5, 2, "4.1", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019")));
        productService.agregarVersionProducto(new VersionProducto(6, 2, "5.2", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2020")));
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
