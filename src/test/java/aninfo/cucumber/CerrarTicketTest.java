package aninfo.cucumber;


import java.text.ParseException;
import java.util.Random;


import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;

import org.junit.Assert;

import com.soporte.model.EstadoTicket;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CerrarTicketTest extends SoporteApplicationTest{


    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;
    Integer id_producto_consultada;

    Integer idVersionProductoValido;
    Integer idVersionProductoInvalido;
    @Before
    public void setup() throws ParseException, java.text.ParseException {
        this.setup_all();
    }
    @After
    public void tearDown() {
        System.out.println("After all test execution");
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
    }

    @When("^El ingeniero de soporte cierre el ticket$")
    public void cierreDeTicket() {
        try {
            ticketService.finalizarTicket(ticketCreado.getNumeroTicket());
        }
        catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^El sistema registra que se cerro el ticket, guarda la fecha de cierre y notifica que se cerro exitosamente$")
    public void sistemaRegistraElCierreGuardaLaFechaDeCierreYAvisaQueSeCerroBien() {
        Assert.assertNotNull(ticketService.getTicketById(ticketCreado.getNumeroTicket()).getFechaCierre());
        Assert.assertTrue(ticketService.getTicketById(ticketCreado.getNumeroTicket()).getEstadoTicket().equals(EstadoTicket.CERRADO));
        ticketService.deleteById(ticketCreado.getNumeroTicket());
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
        ticketService.finalizarTicket(ticketCreado.getNumeroTicket());
    }

    @Then("^El sistema indica que el ticket ya se encuentra cerrado$")
    public void sistemaAvisaQueNoSePuedeCerrarTicketCerrado() {
        Assert.assertEquals(excepcionRecibida.getMessage(), "El ticket ya está cerrado");
        ticketService.deleteById(ticketCreado.getNumeroTicket());
    }

}
