package com.soporte.controllers;

import com.soporte.model.Cliente;
import com.soporte.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class ClienteController {

    @Autowired
	private ClientService clienteExternService;

    @GetMapping("/clientes")
	public Collection<Cliente> getClientes(){
		return clienteExternService.getClientsExterns();
	}

	@GetMapping("/clientes/{id}")
	public Cliente getClientByID(@PathVariable("id") int id){
        return clienteExternService.findById(id);
	}
}
