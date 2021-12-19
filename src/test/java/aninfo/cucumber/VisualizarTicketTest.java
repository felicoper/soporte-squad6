package aninfo.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Random;

import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;
import com.soporte.model.EstadoTicket;

import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.junit.Assert;

public class VisualizarTicketTest extends SoporteApplicationTest {
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;

    Integer legajoCliente = 0;
    Integer idVersionProducto = 0;
    Integer legajoEmpleado = 0;

    @Before
    public void setup() throws ParseException {
        this.setup_all();
    }

    @Given("^un ticket \"([^\"]*)\"$")
    public void unTicket(String arg){
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme D:"; 
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        legajoCliente = 1 + new Random().nextInt(clientes_validos.size() - 1);
        idVersionProducto = 1 + new Random().nextInt(productService.getVersionesProductos().size() - 1);
        legajoEmpleado = 1 + new Random().nextInt(empleados_validos.size() - 1);
    
        ticketRequest = new TicketRequest(titulo, descripcion, legajoCliente, legajoEmpleado, idVersionProducto,
                tipoTicket, severidadTicket);
        ticketCreado = ticketService.createTicket(ticketRequest);
        
        if(arg.equals("inexistente")){
            ticketCreado.setNumeroTicket(-100);
        }
        
    }

    @When("^el ingeniero de soporte consulta el ticket$")
    public void consultarTicket(){
        try {
            ticketService.getTicketById(ticketCreado.getNumeroTicket());
        } catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^el sistema mostrara toda la informacion del ticket$")
    public void mostrarInformacionTicket(){
        Ticket ticket = ticketService.getTicketById(ticketCreado.getNumeroTicket());
        Assert.assertEquals("Problema con SIU", ticket.getTitulo());
        Assert.assertEquals("Problema al querer anotarme D:", ticket.getDescripcion());
        Assert.assertEquals(TipoTicket.CONSULTA, ticket.getTipoTicket());
        Assert.assertEquals(EstadoTicket.ABIERTO, ticket.getEstadoTicket());
        Assert.assertEquals(Severidad.S1, ticket.getSeveridadTicket());
        Assert.assertEquals(legajoCliente, ticket.getIdCliente());
        Assert.assertEquals(idVersionProducto, ticket.getVersionProducto().getId());
        Assert.assertEquals(legajoEmpleado, ticket.getLegajoEmpleado());
    }
    @Then("^el sistema indicara que no existe el ticket solicitado$")
    public void indicarNoExisteTicket(){
        assertEquals(excepcionRecibida.getMessage(), "No existe el ticket con id " + ticketCreado.getNumeroTicket());
    }
}