package com.aninfo.integration.cucumber;

import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.model.Cliente;
import com.soporte.model.EstadoTicket;
import com.soporte.model.Ticket;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class CreacionTest extends SoporteApplicationTest{
    Ticket ticketRequest;
    Ticket ticketCreado;
    ClienteInvalidoExcepcion excepcionRecibida;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
        clientExternService.saveDatabase(new Cliente(100, "FIUBA", "20123456786"));
        clientExternService.saveDatabase(new Cliente(101, "USAL", "20123456783"));
        clientExternService.saveDatabase(new Cliente(102, "UNPAZ", "20123456784"));
        clientExternService.saveDatabase(new Cliente(103, "SA", "20123456780"));
        clientExternService.saveDatabase(new Cliente(104, "SE", "20123456781"));
    }

    @Given("^Los datos validos y obligatorios ingresados para un nuevo ticket$")
    public void validadorDatos() {

        // El cliente con Legajo 100 existe! Está en el "Before"
        ticketRequest = new Ticket(102, 1, 1, EstadoTicket.ABIERTO);

        // Se lo hardcodeo yo mismo! es un Request hardcodeado a manopla! NO LO GENERA SPRINGBOOT.
        ticketRequest.setNumeroTicket(1);
    }

    @Given("^Se ingresaron datos para el ticket pero con algun dato obligatorio faltante$")
    public void ingresoConDatoObligatorioFaltante() {
        ticketRequest = new Ticket(null, 1, 1, EstadoTicket.ABIERTO);
        ticketRequest.setNumeroTicket(1);
    }

    @When("^El ingeniero de soporte crea un nuevo ticket con los datos$")
    public void intentoCrearTicket() {
        try {
            ticketCreado = ticketService.createTicket(ticketRequest);
        } catch (ClienteInvalidoExcepcion excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^El sistema \"([^\"]*)\" el nuevo ticket$")
    public void registroTicket(String registraONo) {
        if (registraONo.equals("registrara")) {
            Assert.assertNotNull(ticketCreado); // Se creó perfecto!

            // Busco en el servicio de tickets mi ticket del request ..
            Assert.assertEquals(ticketService.findById(ticketRequest.getNumeroTicket()).get().getNumeroTicket(), ticketRequest.getNumeroTicket());
            Assert.assertEquals(ticketService.findById(ticketRequest.getNumeroTicket()).get().getIdCliente(), ticketRequest.getIdCliente());
            Assert.assertEquals(ticketService.findById(ticketRequest.getNumeroTicket()).get().getLegajoPersonaAsignada(), ticketRequest.getLegajoPersonaAsignada());

            ticketService.deleteById(ticketRequest.getNumeroTicket()); // lo borro por que... ingenieria.
        } else { // "no registrara"
            Assert.assertNull(ticketCreado); // NO se creó!
            Assert.assertTrue(ticketService.findById(ticketRequest.getNumeroTicket()).isEmpty());
        }
    }

    @And("^Indica un mensaje de exito con identificador y fecha de creacion$")
    public void indicarMensajeExitoYOpcionTarea() {
        Assert.assertEquals(ticketCreado.getNumeroTicket(), ticketRequest.getNumeroTicket());
        Assert.assertEquals(ticketCreado.getFechaCreacion(), ticketRequest.getFechaCreacion());
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");

        clientExternService.deleteById(100);
        clientExternService.deleteById(101);
        clientExternService.deleteById(102);
        clientExternService.deleteById(103);
        clientExternService.deleteById(104);
    }
}
