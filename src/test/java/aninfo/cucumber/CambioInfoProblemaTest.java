package aninfo.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;
import com.soporte.model.EstadoTicket;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.junit.Assert;

public class CambioInfoProblemaTest extends SoporteApplicationTest {
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;

    @Before
    public void setup() throws ParseException {
        this.setup_all();
    }

    @Given("^un ticket con un estado \"([^\"]*)\"$")
    public void ticketConUnEstado(String estadoActual) {
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

    @When("^el ingeniero de soporte cambia \"([^\"]*)\" del ticket$")
    public void CambioTicket(String campo) {
        Map<String, Object> change = new HashMap<>();

        if (campo.equals("el titulo")) {
            change.put("titulo", "Problemas con Facebook");
            try {
                ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);
            } catch (RuntimeException excepcionRecibida) {
                this.excepcionRecibida = excepcionRecibida;
            }
        } else {
            change.put("descripcion", "Problema cuando se intenta agregar amigos");
            try {
                ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);
            } catch (RuntimeException excepcionRecibida) {
                this.excepcionRecibida = excepcionRecibida;
            }
        }
    }

    @When("^el ingeniero de soporte cambia \"([^\"]*)\" del ticket y lo deja vacio$")
    public void cambioVacio(String campo){
        Map<String, Object> change = new HashMap<>();

        if (campo.equals("el titulo")) {
            change.put("titulo", "");
            try {
                ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);
            } catch (RuntimeException excepcionRecibida) {
                this.excepcionRecibida = excepcionRecibida;
            }
        } else {
            change.put("descripcion", "");
            try {
                ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);
            } catch (RuntimeException excepcionRecibida) {
                this.excepcionRecibida = excepcionRecibida;
            }
        }
    }

    @Then("^el sistema registra el cambio \"([^\"]*)\" del ticket$")
    public void registrarCambio(String campo) {
        Ticket ticket = ticketService.getTicketById(ticketCreado.getNumeroTicket());

        if (campo.equals("del titulo")) {
            Assert.assertEquals("Problemas con Facebook", ticket.getTitulo());
        } else {
            Assert.assertEquals("Problema cuando se intenta agregar amigos", ticket.getDescripcion());
        }
    }

    @Then("^el sistema indicara que no se puede cambiar \"([^\"]*)\" de un ticket cerrado$")
    public void indicarQueNoCambiaTicketCerrado(String campo){
        assertEquals(excepcionRecibida.getMessage(), "El ticket ya está cerrado");
    }

    @Then("^el sistema indicara que no se puede cambiar \"([^\"]*)\" del ticket si el campo esta vacio$")
    public void indicarQueNoHayCambioConCampoVacio(String campo){
        assertEquals(excepcionRecibida.getMessage(), "El campo obligatorio esta vacío");
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }

}