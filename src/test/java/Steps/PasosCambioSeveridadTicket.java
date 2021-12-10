package Steps;

import com.soporte.model.Ticket;
import cucumber.api.java.en.*;

public class PasosCambioSeveridadTicket {
    Ticket ticket;

    @Given("^ticket existente con estado \"([^\"]*)\"$")
    public void ticketExistenteConEstado(String estadoActual) {
        // algo...
    }

    @Then("^indica que se cerro exitosamente$")
    public void indica_que_se_cerro_exitosamente() {
        // algo...
    }

    @When("^el ingeniero de soporte cambie la severidad del ticket$")
    public void cambioSeveridadTicket() {
        // algo...
    }

    @Then("^el sistema indica que no se puede cambiar la severidad del ticket$")
    public void indicaQueNoSePuedeCambiarLaSeveridadTicket() {
        // algo...
    }

    @Then("^el sistema registra el cambio de la severidad del ticket$")
    public void registroCambioDeSeveridadTicket() {
        // algo...
    }

    @Then("^indica que se cambio la severidad exitosamente$") // este AND deberia ir??
    public void indica_que_se_cambio_la_severidad_exitosamente() {
        // algo...
    }
}
