package com.aninfo.integration.cucumber;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.soporte.SoporteApplication;
import com.soporte.model.Empleado;
import com.soporte.model.Producto;
import com.soporte.model.VersionProducto;
import com.soporte.model.Cliente;
import com.soporte.repository.TicketRepository;
import com.soporte.service.ClientService;
import com.soporte.service.TicketService;
import com.soporte.service.EmpleadoService;
import com.soporte.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.Before;
import io.cucumber.core.gherkin.messages.internal.gherkin.internal.com.eclipsesource.json.ParseException;

@ContextConfiguration(classes = SoporteApplication.class)
@WebAppConfiguration
@SpringBootTest(classes = SoporteApplication.class)
public class SoporteApplicationTest {
    @Autowired
    protected TicketService ticketService;

    @Autowired
    protected ClientService clientExternService;

    @Autowired
    protected EmpleadoService empleadoService;

    @Autowired
    protected ProductoService productService;

    @Autowired
    protected TicketRepository ticketRepository;

    protected ArrayList<Integer> clientes_validos = new ArrayList<Integer>();
    protected ArrayList<Integer> empleados_validos = new ArrayList<Integer>();

   
    public void setup_all() throws ParseException, java.text.ParseException {
       System.out.println("Before any test execution");

       this.clientes_validos = clientExternService.getClientsExterns().stream().map(Cliente::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
       this.empleados_validos = empleadoService.getEmpleados().stream().map(Empleado::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

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
}