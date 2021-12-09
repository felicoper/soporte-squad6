package com.soporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import com.soporte.model.VersionProducto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.soporte.model.Producto;
import com.soporte.service.ProductoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class SoporteApplication {

	@Autowired
	private ProductoService productService;

	public static void main(String[] args) {
		SpringApplication.run(SoporteApplication.class, args);
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
