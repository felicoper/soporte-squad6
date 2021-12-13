package com.aninfo.integration.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import com.soporte.model.Cliente;
import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;
import com.soporte.model.VersionProducto;
import com.soporte.model.Empleado;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CerrarTicketTest extends SoporteApplicationTest{
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;
    ArrayList<Integer> clientes_validos = new ArrayList<Integer>();
    ArrayList<Integer> empleados_validos = new ArrayList<Integer>();
    Integer id_producto_consultada;
    ArrayList<VersionProducto> versiones_producto = new ArrayList<VersionProducto>();

    Integer idVersionProductoValido;
    Integer idVersionProductoInvalido;
    Integer idTicketCreado;

    @Before
    public void setup() throws ParseException {
       System.out.println("Before any test execution");

       this.clientes_validos = clientExternService.getClientsExterns().stream().map(Cliente::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
       this.empleados_validos = empleadoService.getEmpleados().stream().map(Empleado::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Given("^Ticket existente con estado no cerrado$")
    public void ticketExistenteNoCerradp () {
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

    @When("^El ingeniero de soporte cierre el ticket$")
    public void cierreDeTicket() {
        try {
            ticketCreado.finalizarTicket();
        }
        catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^El sistema registra que se cerro el ticket, guarda la fecha de cierre y notifica que se cerro exitosamente$")
    public void sistemaRegistraElCierreGuardaLaFechaDeCierreYAvisaQueSeCerroBien() {
        assertEquals(excepcionRecibida.getMessage(), "Se cerró el ticket correctamente");
    }

    @Given("^Ticket existente con estado cerrado$")
    public void ticketExistenteCerrado() {
        idVersionProductoValido = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);

        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme D:";
        Integer legajoClienteValido = 1 + new Random().nextInt(clientes_validos.size() - 1);
        Integer legajoEmpleadoValido = 1 + new Random().nextInt(empleados_validos.size() - 1);
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        ticketRequest = new TicketRequest(titulo, descripcion, legajoClienteValido, legajoEmpleadoValido, idVersionProductoValido, tipoTicket, severidadTicket);

        ticketCreado = ticketService.createTicket(ticketRequest);
        try {
            ticketCreado.finalizarTicket();
        }
        catch (RuntimeException e) {
            // no hace nada
        }
    }

    @Then("^El sistema indica que el ticket ya se encuentra cerrado$")
    public void sistemaAvisaQueNoSePuedeCerrarTicketCerrado() {
        assertEquals(excepcionRecibida.getMessage(), "El ticket se encuentra cerrado");
    }
}
