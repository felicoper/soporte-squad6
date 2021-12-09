package Steps;

import cucumber.api.java.en.*;

import com.aninfo.integration.cucumber.SoporteApplicationTest;

import org.junit.jupiter.api.Assertions;

public class PasosCierreTicket extends SoporteApplicationTest {

    @Given("ticket existente con estado \"([^\"]*)\"")
    public void ticketExistenteConEstado(String estadoActual) {

    }

    @When("el ingeniero de soporte cierre el ticket")
    public void cierreTicket() {

    }

    @Then("^el sistema registra que se cerro el ticket y notifica que se cerro exitosamente$")
    public void registraCierreTicketYNotifica() {
    }

    @Then("^el sistema indica que el ticket ya se encuentra cerrado$")
    public void indicaQueElTicketEstaCerrado() {
    }
}
