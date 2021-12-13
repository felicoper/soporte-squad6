package com.aninfo.integration.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import com.soporte.model.Cliente;
import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;
import com.soporte.model.Empleado;
import com.soporte.model.EstadoTicket;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class CambioPersonaAsignadaTest extends SoporteApplicationTest {
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;

    Integer legajoEmpleado = 0;
    Integer legajoEmpleadoAnterior = 0;

  
    @Before
    public void setup() throws ParseException {
        this.setup_all();
    }
  
    private Integer obtenerLegajoEmpleadoInexistente() {
        int i = clientes_validos.size();
        while (i > 0) {
            i++;
            if (!empleados_validos.contains(i)) {
                return i;
            }
        }
        return null;
    }

    @Given("^un ticket existente no cerrado y los datos de una persona \"([^\"]*)\" en el sistema$")
    public void ticketNoCerradoYDatosDePersona(String arg) {
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme D:";
        Integer legajoCliente = 1 + new Random().nextInt(clientes_validos.size() - 1);
        Integer idVersionProducto = 1 + new Random().nextInt(productService.getVersionesProductos().size() - 1);
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        EstadoTicket estadoTicket = EstadoTicket.ABIERTO;

        Map<String, Object> change = new HashMap<>();
        change.put("estado", estadoTicket);

        if (arg.equals("existente")) {
            legajoEmpleado = 1 + new Random().nextInt(empleados_validos.size() - 1);
            ticketRequest = new TicketRequest(titulo, descripcion, legajoCliente, legajoEmpleado, idVersionProducto,
                    tipoTicket, severidadTicket);
        } else {
            legajoEmpleado = this.obtenerLegajoEmpleadoInexistente();
            Integer legajoEmpleadoPrevio = 1 + new Random().nextInt(empleados_validos.size() - 1);
            ticketRequest = new TicketRequest(titulo, descripcion, legajoCliente, legajoEmpleadoPrevio,
                    idVersionProducto,
                    tipoTicket, severidadTicket);
        }

        ticketCreado = ticketService.createTicket(ticketRequest);
        ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);
    }

    @Given("^un ticket existente cerrado$")
    public void ticketCerrado() {
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme D:";
        Integer legajoCliente = 1 + new Random().nextInt(clientes_validos.size() - 1);
        Integer idVersionProducto = 1 + new Random().nextInt(productService.getVersionesProductos().size() - 1);
        legajoEmpleado = 1 + new Random().nextInt(empleados_validos.size() - 1);
        legajoEmpleadoAnterior = 1 + new Random().nextInt(empleados_validos.size() - 1);
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        EstadoTicket estadoTicket = EstadoTicket.CERRADO;

        Map<String, Object> change = new HashMap<>();
        change.put("estado", estadoTicket);

        ticketRequest = new TicketRequest(titulo, descripcion, legajoCliente, legajoEmpleadoAnterior, idVersionProducto,
                tipoTicket, severidadTicket);
        ticketCreado = ticketService.createTicket(ticketRequest);
        ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);
    }

    @When("^el ingeniero de soporte cambie la persona asignada$")
    public void cambiarPersonaAsignada() {
        Map<String, Object> change = new HashMap<>();
        change.put("legajoEmpleado", legajoEmpleado);

        try {
            ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);
        } catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^el sistema registrara el cambio de la persona asignada en el ticket e indicara que se cambio exitosamente$")
    public void registrarCambioPersonaAsignada() {

        Assert.assertEquals(legajoEmpleado, ticketCreado.getLegajoEmpleado());
    }

    @Then("^el sistema indica que no se puede cambiar la persona asignada a un ticket cerrado$")
    public void indicarQueNoCambiaPersonaAsignadaEnTicketCerrado() {
        Assert.assertEquals(legajoEmpleadoAnterior, ticketCreado.getLegajoEmpleado());
        // me fijo que el empleado en el ticket sea el mismo que estaba antes de
        // intentar cambiar al empleado
    }

    @Then("^el sistema no registrara la persona asignada en el ticket y solicitara que ingrse los datos de una persona existente en la empresa$")
    public void noRegistrarPersonaAsignada() {
        assertEquals(excepcionRecibida.getMessage(), "El empleado no pertenece a la empresa.");
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
