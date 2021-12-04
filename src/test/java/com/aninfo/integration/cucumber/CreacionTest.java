package com.aninfo.integration.cucumber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.model.Cliente;
import com.soporte.model.EstadoTicket;
import com.soporte.model.Producto;
import com.soporte.model.Ticket;
import com.soporte.model.TipoTicket;
import com.soporte.model.VersionProducto;
import com.soporte.model.Empleado;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Date;  
import org.junit.Assert;

public class CreacionTest extends SoporteApplicationTest{
    Ticket ticketRequest;
    Ticket ticketCreado;
    ClienteInvalidoExcepcion excepcionRecibida;

    @Before
    public void setup() throws ParseException {
        System.out.println("Before any test execution");
        clientExternService.saveDatabase(new Cliente(100, "FIUBA", "20123456786"));
        clientExternService.saveDatabase(new Cliente(101, "USAL", "20123456783"));
        clientExternService.saveDatabase(new Cliente(102, "UNPAZ", "20123456784"));
        clientExternService.saveDatabase(new Cliente(103, "SA", "20123456780"));
        clientExternService.saveDatabase(new Cliente(104, "SE", "20123456781"));

        empleadoService.saveDatabase(new Empleado(100898, "Fernando", "Bursztyn"));
        empleadoService.saveDatabase(new Empleado(23456, "Federico", "noseElApellido"));
        empleadoService.saveDatabase(new Empleado(112233, "Martin", "Carlos"));
        empleadoService.saveDatabase(new Empleado(445566, "Felipe", "Copertini"));
        empleadoService.saveDatabase(new Empleado(778899, "Mateo", "Bulnes"));

        
        Producto producto1 = new Producto(001, "SIU", new VersionProducto(1, "0.99b", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2018")));
        producto1.agregarVersion(new VersionProducto(-2, "0.99", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2018")));
        productService.crearProducto(producto1);
        productService.agregarVersionProducto(producto1, new VersionProducto(2, "1.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019")));
        productService.agregarVersionProducto(producto1, new VersionProducto(3, "1.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2020")));
        
        Producto producto2 = new Producto(002, "Linux", new VersionProducto(4, "1.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2018")));
        productService.crearProducto(producto2);
        productService.agregarVersionProducto(producto2, new VersionProducto(5, "1.1", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019")));
        productService.agregarVersionProducto(producto2, new VersionProducto(6, "1.2", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2020")));
    }

    @Given("^Los datos validos y obligatorios ingresados para un nuevo ticket$")
    public void validadorDatos() {
        // El cliente con Legajo 100 existe! Está en el "Before"
        //public Ticket(String titulo, String descripcion, Integer legajoCliente, Integer legajoEmpleado, Integer numeroVrsionProducto, TipoTicket tipoTicket,Integer idVerion) {
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme con Argerich y Mendez a la vez D:";
        Integer legajoCliente = 100;
        Integer legajoEmpleado = 23456;
        Integer idVersionProducto = 5;
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        ticketRequest = new Ticket(titulo, descripcion, legajoCliente, legajoEmpleado, idVersionProducto, tipoTicket);

        // Se lo hardcodeo yo mismo! es un Request hardcodeado a manopla! NO LO GENERA SPRINGBOOT.
        ticketRequest.setNumeroTicket(1);
    }

    @Given("^Se ingresaron datos para el ticket pero con algun dato obligatorio faltante$")
    public void ingresoConDatoObligatorioFaltante() {
        Integer legajoClienteInexistente = null;

        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme con Argerich y Mendez a la vez D:";
        Integer legajoEmpleado = 23456;
        Integer idVersionProducto = 5;
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        ticketRequest = new Ticket(titulo, descripcion, legajoClienteInexistente, legajoEmpleado, idVersionProducto, tipoTicket);

        ticketRequest.setNumeroTicket(1);
    }

    @When("^El ingeniero de soporte crea un nuevo ticket con los datos$")
    public void intentoCrearTicket() {
        try {
            ticketCreado = ticketService.createTicket(ticketRequest);
        } catch (ClienteInvalidoExcepcion excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^El sistema \"([^\"]*)\" el nuevo ticket$")
    public void registroTicket(String registraONo) {
        if (registraONo.equals("registrara")) {
            Assert.assertNotNull(ticketCreado); // Se creó perfecto!

            // Busco en el servicio de tickets mi ticket del request ..
            Assert.assertEquals(ticketService.findById(ticketRequest.getNumeroTicket()).get().getNumeroTicket(), ticketRequest.getNumeroTicket());
            Assert.assertEquals(ticketService.findById(ticketRequest.getNumeroTicket()).get().getIdCliente(), ticketRequest.getIdCliente());
            Assert.assertEquals(ticketService.findById(ticketRequest.getNumeroTicket()).get().getLegajoEmpleado(), ticketRequest.getLegajoEmpleado());

            ticketService.deleteById(ticketRequest.getNumeroTicket()); 
        } else { // "no registrara"
            Assert.assertNull(ticketCreado); // NO se creó!
            Assert.assertTrue(ticketService.findById(ticketRequest.getNumeroTicket()).isEmpty());
        }
    }

    /*@And("Indica un mensaje de exito con identificador y fecha de creacion")
    public void indicarMensajeExitoYOpcionTarea() {
        Assert.assertEquals(ticketCreado.getNumeroTicket(), ticketRequest.getNumeroTicket());
        Assert.assertEquals(ticketCreado.getFechaCreacion(), ticketRequest.getFechaCreacion());
    }*/

    @After
    public void tearDown() {
        System.out.println("After all test execution");

        clientExternService.deleteById(100);
        clientExternService.deleteById(101);
        clientExternService.deleteById(102);
        clientExternService.deleteById(103);
        clientExternService.deleteById(104);
    }
}
