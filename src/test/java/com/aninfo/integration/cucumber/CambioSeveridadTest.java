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
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class CambioSeveridadTest extends SoporteApplicationTest{
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;
    ArrayList<Integer> clientes_validos = new ArrayList<Integer>();
    ArrayList<Integer> empleados_validos = new ArrayList<Integer>();

    @Before
    public void setup() throws ParseException {
       System.out.println("Before any test execution");

       this.clientes_validos = clientExternService.getClientsExterns().stream().map(Cliente::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
       this.empleados_validos = empleadoService.getEmpleados().stream().map(Empleado::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Given("^ticket existente con estado no cerrado$")
    public void ticket_existente_con_estado_no_cerrado()  {
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme D:";
        Integer legajoClienteValido = 1 + new Random().nextInt(clientes_validos.size() - 1);
        Integer legajoEmpleadoValido = 1 + new Random().nextInt(empleados_validos.size() - 1);
        Integer idVersionProductoValido = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        ticketRequest = new TicketRequest(titulo, descripcion, legajoClienteValido, legajoEmpleadoValido, idVersionProductoValido, tipoTicket, severidadTicket);
        ticketCreado = ticketService.createTicket(ticketRequest);
    }
    

    @When("^El ingeniero de soporte cambie la severidad del ticket$")
    public void cambiar_severidad_de_un_ticket(){ 
        try {
            Map<String, Object> change = new HashMap<>();
            change.put("severidadTicket", Severidad.S3);
            ticketCreado = ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);   
        } catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^El sistema registra el cambio de la severidad del ticket$")
    public void sistema_registra_el_cambio_de_severidad(){
        assertEquals(Severidad.S3, ticketCreado.getSeveridadTicket());
    }

    @Given("^ticket existente con estado cerrado$")
    public void ticket_existente_con_estado_cerrado()  {
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme D:";
        Integer legajoClienteValido = 1 + new Random().nextInt(clientes_validos.size() - 1);
        Integer legajoEmpleadoValido = 1 + new Random().nextInt(empleados_validos.size() - 1);
        Integer idVersionProductoValido = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        ticketRequest = new TicketRequest(titulo, descripcion, legajoClienteValido, legajoEmpleadoValido, idVersionProductoValido, tipoTicket, severidadTicket);
        ticketCreado = ticketService.createTicket(ticketRequest);
        
        Map<String, Object> change = new HashMap<>();
        change.put("estado",EstadoTicket.CERRADO);
        ticketCreado = ticketService.updateTicket(ticketCreado.getNumeroTicket(), change);
    
        
    }

    @Then("^el sistema indica que no se puede cambiar la severidad del ticket$")
    public void sistema_no_registra_el_cambio_de_severidad(){
        assertEquals(excepcionRecibida.getMessage(),"El ticket ya está cerrado");
   
    }
}
