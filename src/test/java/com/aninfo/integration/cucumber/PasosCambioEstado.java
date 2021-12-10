package com.aninfo.integration.cucumber;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.soporte.model.Producto;
import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;
import com.soporte.model.VersionProducto;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PasosCambioEstado extends SoporteApplicationTest {
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;

    @Before
    public void setup() throws ParseException {
        System.out.println("Before any test execution");

        Producto producto1 = new Producto(1, "SIU Guarani");
        Producto producto2 = new Producto(2, "Linux");
        productService.saveDatabase(producto1);
        productService.saveDatabase(producto2);
        VersionProducto versionProducto1 = new VersionProducto(1, "0.99b",
                new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2018"));
        VersionProducto versionProducto2 = new VersionProducto(2, "3.0",
                new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2018"));
        VersionProducto versionProducto3 = new VersionProducto(3, "1.0",
                new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019"));
        VersionProducto versionProducto4 = new VersionProducto(4, "2.0",
                new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2020"));
        VersionProducto versionProducto5 = new VersionProducto(5, "4.1",
                new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019"));
        VersionProducto versionProducto6 = new VersionProducto(6, "5.2",
                new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2020"));

        versionProducto1.setProducto(producto1);
        versionProducto2.setProducto(producto2);
        versionProducto3.setProducto(producto1);
        versionProducto4.setProducto(producto1);
        versionProducto5.setProducto(producto2);
        versionProducto6.setProducto(producto2);

        productService.saveDatabaseVersion(versionProducto1);
        productService.saveDatabaseVersion(versionProducto2);
        productService.saveDatabaseVersion(versionProducto3);
        productService.saveDatabaseVersion(versionProducto4);
        productService.saveDatabaseVersion(versionProducto5);
        productService.saveDatabaseVersion(versionProducto6);

    }

    @Given("^ticket existente con estado \"([^\"]*)\"$")
    public void ticketExistenteEstadoNoCerrado(String estadoActual) {
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme con Argerich y Mendez a la vez D:";
        Integer legajoCliente = 100;
        Integer legajoEmpleado = 23456;
        Integer idVersionProducto = 1;
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        ticketRequest = new TicketRequest(titulo, descripcion, legajoCliente, legajoEmpleado, idVersionProducto,
                tipoTicket, severidadTicket);
        ticketCreado = ticketService.createTicket(ticketRequest);
    }

    @When("^el ingeniero de soporte cambie el ticket al nuevo estado \"([^\"]*)\"$")
    public void cambioEstadoANuevoEstadoNoCerrado(String nuevoEstado) {
        //ticketService.updateTicket(ticketCreado.getNumeroTicket(), /*aca iria un Map*/); //cambia a nuevoEstado

    }

    @When("^el ingeniero de soporte intente cambiar el estado del ticket$")
    public void cambioEstado() {
        // this.ticket.cambiarEstado(Estado.ABIERTO);
    }

    @Then("^el sistema registra el cambio de estado del ticket$")
    public void registroCambioDeEstado() {
        // Assertions.assertEquals(Estado.ECLIENTE, this.ticket.getEstado());
    }

    @Then("^indica que se cambio el estado exitosamente$")
    public void indicarCambioExitoso() {
        System.out.println("Se realizo el cambio de estado exitosamente");
    }

    @Then("^el sistema indica que pasara a cerrar el ticket y no se podra volver a cambiar el estado$")
    public void indicarCierreTicket() {
        System.out.println("Se cerrara el ticket");
    }

    @Then("^pregunta si desea continuar con el cierre del ticket$")
    public void preguntarSiContinuaConCierre() {
        System.out.println("Desea continuar con el cierre del ticket?");
    }

    @Then("^el sistema indica que no se puede cambiar el estado de un ticket cerrado$")
    public void indicarQueNoSePuedeCambiarEstadoDeTicketCerrado() {
        System.out.println("No puede cambiar el estado de un ticket cerrado");
    }
}
