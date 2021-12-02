package com.aninfo.integration.cucumber;

import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.model.Cliente;
import com.soporte.model.EstadoTicket;
import com.soporte.model.Ticket;
import com.soporte.repository.TicketRepository;
import com.soporte.service.ClientExternService;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class PasosCreacionTicket extends SoporteServiceTest {

    Ticket ticketRequest;
    Ticket ticketCreado;
    ClienteInvalidoExcepcion exception;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
        MockitoAnnotations.initMocks(this);
        clientExternService = Mockito.mock(ClientExternService.class);
        ticketRepository = Mockito.mock(TicketRepository.class);

        Cliente mockCliente1 = new Cliente(100, "FIUBA", "20123456786");
        Cliente mockCliente2 = new Cliente(101, "USAL", "20123456787");
        Cliente mockCliente3 = new Cliente(102, "UNPAZ", "20123456788");

        Collection<Cliente> mockList = new ArrayList<Cliente>();
        mockList.add(mockCliente1);
        mockList.add(mockCliente2);
        mockList.add(mockCliente3);

        when(clientExternService.getClients()).thenReturn(mockList);
        when(clientExternService.findById(100)).thenReturn(mockCliente1);
        when(clientExternService.findById(101)).thenReturn(mockCliente2);
        when(clientExternService.findById(101)).thenReturn(mockCliente3);

        when(ticketRepository.findById(100)).thenReturn(Optional.of(100));
    }


    @Given("^Los datos validos y obligatorios ingresados para un nuevo ticket$")
    public void validadorDatos(){
        ticketRequest = new Ticket(100, 1, 1, EstadoTicket.ABIERTO);
        ticketRequest.setNumeroTicket(1); // Se lo hardcodeo yo mismo, si no NO ME PONE NINGUN $!"#$ numeroTicket.
    }




    @When("^El ingeniero de soporte crea un nuevo ticket con los datos$")
    public void intentoCrearTicket(){
        try{
            ticketCreado = this.createTicket(ticketRequest);
        }catch (ClienteInvalidoExcepcion e){
            exception = e;
        }
    }




    @Then("^El sistema registrara el nuevo ticket$")
    public void registroTicket(){
        Assert.assertNotNull(ticketCreado);
        Assert.assertEquals(ticketService.getTickets().size(), 1);
        Assert.assertEquals(ticketService.findById(ticketCreado.getNumeroTicket()), ticketRequest.getNumeroTicket());
        Assert.assertEquals(ticketService.findById(ticketCreado.getIdCliente()), ticketRequest.getIdCliente());
        Assert.assertEquals(ticketService.findById(ticketCreado.getLegajoPersonaAsignada()), ticketRequest.getLegajoPersonaAsignada());

    }

    @And("^Indica un mensaje de exito con identificador y fecha de creacion$")
    public void indicarMensajeExitoYOpcionTarea(){
        Assert.assertEquals(ticketCreado.getNumeroTicket(), ticketRequest.getNumeroTicket());
        Assert.assertEquals(ticketCreado.getFechaCreacion(), ticketRequest.getFechaCreacion());
    }



    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
