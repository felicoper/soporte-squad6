package com.aninfo.integration.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import com.soporte.model.Cliente;
import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;
import com.soporte.model.Empleado;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ConsultaTicketsTest extends SoporteApplicationTest {
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;
    ArrayList<Integer> clientes_validos = new ArrayList<Integer>();
    ArrayList<Integer> empleados_validos = new ArrayList<Integer>();
    Integer id_producto_consultada;
    ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    Integer idVersionProductoValido;
    Integer idVersionProductoValidoUno;
    Integer idVersionProductoInvalido;
    Integer idTicketCreado;
    
    @Before
    public void setup() throws ParseException {
        System.out.println("Before any test execution");
 
        this.clientes_validos = clientExternService.getClientsExterns().stream().map(Cliente::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        this.empleados_validos = empleadoService.getEmpleados().stream().map(Empleado::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Given("^Hay tickets asociados a una versión de producto determinada$")
    public void ticketsEnSistemaSobreVersionDeProducto() {
        idVersionProductoValido = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);

        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme D:";
        Integer legajoClienteValido = 1 + new Random().nextInt(clientes_validos.size() - 1);
        Integer legajoEmpleadoValido = 1 + new Random().nextInt(empleados_validos.size() - 1);
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        ticketRequest = new TicketRequest(titulo, descripcion, legajoClienteValido, legajoEmpleadoValido, idVersionProductoValido, tipoTicket, severidadTicket);

        ticketCreado = ticketService.createTicket(ticketRequest);
        idTicketCreado = ticketCreado.getNumeroTicket();
    }

    @When("^El ingeniero de soporte consulte los tickets de una version de producto$")
    public void cosultaTicketsDeUnaVersion() {
        try {
            tickets = (ArrayList<Ticket>) productService.getTicketsOfVersion(idVersionProductoValido);
        }
        catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
        
    }

    @Then("^El sistema muestra el conjunto de todos los tickets registrados$")
    public void sistemaMuestraLosTickets() {
        assertNotNull(tickets);
        ticketService.deleteById(idTicketCreado);
    }
    
    @Given("^No hay tickets asociados a una versión de producto determinada$")
    public void ticketsEnSistemaNoSobreVersionDeProducto() {
        idVersionProductoValido = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);
    }

    @Then("^El sistema mostrara un mensaje donde explica que no hay tickets registrados en el sistema para esa version del producto$")
    public void sistemaMuestraMensajeDeError() {
        assertEquals(excepcionRecibida.getMessage(), "La version de producto no tiene tickets registrados");
    }
    
}