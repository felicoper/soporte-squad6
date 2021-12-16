package aninfo.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
public class ConsultaTicketsTest extends SoporteApplicationTest {
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;
    Integer id_producto_consultada;
    ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    Integer idVersionProductoValido;
    Integer idVersionProductoValidoUno;
    Integer idVersionProductoInvalido;

    @Before
    public void setup() throws ParseException {
        this.setup_all();
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
        ticketService.deleteById(ticketCreado.getNumeroTicket());
    }
    
    @Given("^No hay tickets asociados a una versión de producto determinada$")
    public void ticketsEnSistemaNoSobreVersionDeProducto() {
        idVersionProductoValido = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);
    }

    @Then("^El sistema mostrara un mensaje donde explica que no hay tickets registrados en el sistema para esa version del producto$")
    public void sistemaMuestraMensajeDeError() {
        assertEquals(excepcionRecibida.getMessage(), "La version de producto no tiene tickets registrados");
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
    
}
