package com.soporte.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.soporte.model.VersionProducto;

import java.util.Collection;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.soporte.model.Producto;
import com.soporte.service.ProductoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class ProductoController {

    @Autowired
	private ProductoService productService;

	@GetMapping("/productos")
	public Collection<Producto> getProducto(){
		return productService.getProductos();
	}

	@GetMapping("/versiones-productos")
	public Collection<VersionProducto> getVersionesProducto(){
		return productService.getVersionesProductos();
	}
}
