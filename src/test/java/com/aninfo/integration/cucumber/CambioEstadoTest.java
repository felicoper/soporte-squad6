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
import com.soporte.model.VersionProducto;
import com.soporte.repository.VersionProductoRepository;
import com.soporte.service.ProductoService;
import com.soporte.model.Empleado;
import com.soporte.model.EstadoTicket;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.bytebuddy.agent.builder.AgentBuilder.Listener;

import org.hibernate.mapping.Collection;
import org.junit.Assert;

public class CambioEstadoTest extends SoporteApplicationTest {
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;
    ArrayList<Integer> clientes_validos = new ArrayList<Integer>();
    ArrayList<Integer> empleados_validos = new ArrayList<Integer>();

    Integer ticketPosible;
    Integer ticketCerrado;

    @Before
    public void setup() throws ParseException {
        System.out.println("Before any test execution");

        this.clientes_validos = clientExternService.getClientsExterns().stream().map(Cliente::getId)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        this.empleados_validos = empleadoService.getEmpleados().stream().map(Empleado::getId).collect(ArrayList::new,
                ArrayList::add, ArrayList::addAll);
    }

    @Given("^ticket existente con estado \"([^\"]*)\"$")
    public void ticketExistenteEstadoNoCerrado(String estadoActual) {
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme D:";
        Integer legajoCliente = 1 + new Random().nextInt(clientes_validos.size() - 1);
        Integer idVersionProducto = 1 + new Random().nextInt(productService.getVersionesProductos().size() - 1);
        Integer legajoEmpleado = 1 + new Random().nextInt(empleados_validos.size() - 1);
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;

        Map<String, Object> change = new HashMap<>();

        ticketRequest = new TicketRequest(titulo, descripcion, legajoCliente, legajoEmpleado, idVersionProducto,
                tipoTicket, severidadTicket);
        ticketCreado = ticketService.createTicket(ticketRequest);
        if (estadoActual.equals("cerrado")) {
            change.put("estado", EstadoTicket.CERRADO);
            ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);
        }
    }

    @When("^el ingeniero de soporte cambie el ticket al nuevo estado \"([^\"]*)\"$")
    public void cambioEstadoANuevoEstado(String nuevoEstado) {
        Map<String, Object> cambio = new HashMap<>();
        if (nuevoEstado.equals("no cerrado")) {
            cambio.put("estado", EstadoTicket.ECLIENTE);
            ticketPosible = ticketCreado.getNumeroTicket();
        } else {
            cambio.put("estado", EstadoTicket.CERRADO);
            ticketCerrado = ticketCreado.getNumeroTicket();
        }

        try {
            ticketService.updateTicket(ticketCreado.getNumeroTicket(), cambio);
        } catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }

    }

    @When("^el ingeniero de soporte intente cambiar el estado del ticket$")
    public void cambioEstado() {
        Map<String, Object> change = new HashMap<>();
        change.put("estado", EstadoTicket.EDESARROLLO);

        try {
            ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);
        } catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^el sistema registra el cambio de estado del ticket$")
    public void registroCambioDeEstado() {
        Assert.assertEquals(EstadoTicket.ECLIENTE, ticketCreado.getEstadoTicket(),);
        
    }

    @Then("^indica que se cambio el estado exitosamente$")
    public void indicarCambioExitoso() {
        // ...
    }

    @Then("^el sistema indica que pasara a cerrar el ticket y no se podra volver a cambiar el estado$")
    public void indicarCierreTicket() {
        Assert.assertEquals(EstadoTicket.CERRADO, ticketCreado.getEstadoTicket());
    }

    @Then("^pregunta si desea continuar con el cierre del ticket$")
    public void preguntarSiContinuaConCierre() {
        // ...
    }

    @Then("^el sistema indica que no se puede cambiar el estado de un ticket cerrado$")
    public void indicarQueNoSePuedeCambiarEstadoDeTicketCerrado() {
        assertEquals(excepcionRecibida.getMessage(), "El ticket ya está cerrado");
    }
}
