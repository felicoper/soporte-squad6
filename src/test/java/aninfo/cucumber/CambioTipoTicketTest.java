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

public class CambioTipoTicketTest extends SoporteApplicationTest {
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;

    @Before
    public void setup() throws ParseException {
        this.setup_all();
    }

    @Given("^ticket existente con estado \"([^\"]*)\"$")
    public void ticketExistenteConEstado(String estadoActual) {
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

    @When("^el ingeniero de soporte cambie el tipo del ticket$")
    public void cambioTipoTicket() {
        Map<String, Object> change = new HashMap<>();
        change.put("tipoTicket", TipoTicket.INCIDENCIA);

        try {
            ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);
        } catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^el sistema registra el cambio de tipo del ticket$")
    public void registrarCambioTipoTicket() {
        Assert.assertEquals(TipoTicket.INCIDENCIA, ticketCreado.getTipoTicket());
    }

    @Then("^el sistema indica que no se puede cambiar el tipo de un ticket cerrado$")
    public void indicaQueNoCambiaTipoTicketDeTicketCerrado() {
        assertEquals(excepcionRecibida.getMessage(), "El ticket ya está cerrado");
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }

}
