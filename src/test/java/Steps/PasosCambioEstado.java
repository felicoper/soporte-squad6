package Steps;
import cucumber.api.java.en.*;
import org.junit.jupiter.api.Assertions;

public class PasosCambioEstado {
    Ticket ticket;

    @Given("ticket existente con estado no cerrado")
    public void ticketExistenteEstadoNoCerrado(){
        this.ticket = new Ticket("Abierto");
    }
    @Given("ticket existente con estado cerrado")
    public void ticketExistenteEstadoCerrado(){
        this.ticket = new Ticket("Cerrado");
    }
    @When("el ingeniero de soporte cambie el ticket al nuevo estado no cerrado")
    public void cambioEstadoANuevoEstadoNoCerrado(){
        this.ticket.cambiarEstado("Esperando cliente");
    }
    @When("el ingeniero de soporte cambie el ticket al nuevo estado cerrado")
    public void cambioEstadoANuevoEstadoCerrado(){
        this.ticket.cambiarEstado("Cerrado");
    }
    @When("el ingeniero de soporte intente cambiar el estado del ticket")
    public void cambioEstado(){
        this.ticket.cambiarEstado("Abierto");
    }
    @Then("el sistema registra el cambio de estado del ticket")
    public void registroCambioDeEstado(){
        Assertions.assertEquals("Esperando cliente", this.ticket.getEstado());
    }
    @Then("indica que se cambio el estado exitosamente")
    public void indicarCambioExitoso(){
        System.out.println("Se realizo el cambio de estado exitosamente");
    }
    @Then("el sistema indica que pasara a cerrar el ticket y no se podra volver a cambiar el estado")
    public void indicarCierreTicket(){
        System.out.println("Se cerrara el ticket");
    }
    @Then("pregunta si desea continuar con el cierre del ticket")
    public void preguntarSiContinuaConCierre(){
        System.out.println("Desea continuar con el cierre del ticket?");
    }
    @Then("el sistema indica que no se puede cambiar el estado de un ticket cerrado")
    public void indicarQueNoSePuedeCambiarEstadoDeTicketCerrado(){
        System.out.println("No puede cambiar el estado de un ticket cerrado");
    }
}
