package com.soporte;

import com.soporte.model.Cliente;
import com.soporte.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.VersionProducto;
import com.soporte.service.TicketService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

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

	@GetMapping("/tickets/{id_producto}")
	public Collection<Ticket> getTicketsOfVersionProduct(@PathVariable Integer id_producto) {
		return productService.getTicketsOfVersion(id_producto);
	}

	////////// https://nullbeans.com/using-put-vs-patch-when-building-a-rest-api-in-spring/
	@PatchMapping("/tickets/{id_ticket}")
	public Ticket updateTicket(@RequestBody String toChange, @PathVariable Integer id_ticket) 
	{
		return ticketService.updateTicket(toChange, id_ticket);
		//return fields;
	}

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
		Producto producto1 = new Producto(1, "SIU Guarani");
        Producto producto2 = new Producto(2, "Linux");
        productService.saveDatabase(producto1);
        productService.saveDatabase(producto2);
		VersionProducto versionProducto1 = new VersionProducto(1, "0.99b", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2018"));
		VersionProducto versionProducto2 = new VersionProducto(2, "3.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2018"));
        VersionProducto versionProducto3 = new VersionProducto(3, "1.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019"));
        VersionProducto versionProducto4 = new VersionProducto(4, "2.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2020"));
        VersionProducto versionProducto5 = new VersionProducto(5, "4.1", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019"));
        VersionProducto versionProducto6 = new VersionProducto(6, "5.2", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2020"));

		versionProducto1.setProducto(producto1);
		versionProducto2.setProducto(producto2);
		versionProducto3.setProducto(producto1);
		versionProducto4.setProducto(producto1);
		versionProducto5.setProducto(producto2);
		versionProducto6.setProducto(producto2);

		productService.saveDatabaseVersion(versionProducto1);
		productService.saveDatabaseVersion(versionProducto2);
		productService.saveDatabaseVersion(versionProducto3);
		productService.saveDatabaseVersion(versionProducto4);
		productService.saveDatabaseVersion(versionProducto5);
		productService.saveDatabaseVersion(versionProducto6);

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
