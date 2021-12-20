package aninfo.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
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

import java.util.Set;

import org.junit.Assert;

public class GeneracionTareaTicketTest extends SoporteApplicationTest {
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;

    Integer legajoCliente = 0;
    Integer idVersionProducto = 0;
    Integer legajoEmpleado = 0;
    String idTarea;

    @Before
    public void setup() throws ParseException {
        this.setup_all();
    }

    @Given("^un ticket no cerrado y una tarea con su identificador \"([^\"]*)\" a cero$")
    public void ticketNoCerradoYTareaConId(String arg){
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme D:";
        Integer legajoCliente = 1 + new Random().nextInt(clientes_validos.size() - 1);
        Integer idVersionProducto = 1 + new Random().nextInt(productService.getVersionesProductos().size() - 1);
        Integer legajoEmpleado = 1 + new Random().nextInt(empleados_validos.size() - 1);
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;

        ticketRequest = new TicketRequest(titulo, descripcion, legajoCliente, legajoEmpleado, idVersionProducto,
                tipoTicket, severidadTicket);
        ticketCreado = ticketService.createTicket(ticketRequest);

        if(arg.equals("mayor")){
            idTarea = "2";
        }
        else{
            idTarea = "-1";
        }
    }

    @When("^el ingeniero de soporte asigne una tarea a un ticket$")
    public void asignarTareaTicket(){
        try {
            ticketCreado.addTarea(idTarea);
        } catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^el sistema registrara correctamente la tarea al ticket$")
    public void registrarTareaTicket(){
        Set<String> tareas = ticketCreado.getIdTareas();
        Boolean resultado = false;
        if(tareas.contains(idTarea)){
            resultado = true; 
        }
        Assert.assertTrue(resultado);
    }

    @Then("^el sistema no registrara correctamente la tarea al ticket indicando que no se puede asignar una tarea con identificador menor o igual a cero$")
    public void noRegistrarTareaTicket(){
        assertEquals(excepcionRecibida.getMessage(), "No se puede asignar una tarea con ID menor o igual a cero");
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}