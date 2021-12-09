package com.soporte.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.soporte.model.Empleado;
import com.soporte.service.EmpleadoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class EmpleadoController {

    @Autowired
	private EmpleadoService empleadoService;

	@GetMapping("/empleados")
	public Collection<Empleado> getEmpleados(){
		return empleadoService.getEmpleados();
	}

	@GetMapping("/empleados/{id}")
	public Empleado getEmpleadosByID(@PathVariable("id") int id){
        return empleadoService.findById(id);
	}
}
