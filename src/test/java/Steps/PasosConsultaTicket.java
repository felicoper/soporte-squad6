package Steps;

import cucumber.api.java.en.*;
import com.aninfo.integration.cucumber.SoporteApplicationTest;

public class PasosConsultaTicket extends SoporteApplicationTest {

    @Given("^hay tickets registrados en el sistema y \"([^\"]*)\"$")
    public void hayTicketsRegistrados(String registradoSobreVersionONo) {
    }

    @When("^el ingeniero de soporte consulte los tickets de una version de producto$")
    public void consultaTicketsDeUnaVersionDeProducto() {

    }

    @Then("^el sistema muestra el conjunto de todos los tickets registrados$")
    public void mostrarConjuntoDeTicketsRegistrados() {

    }

    @Then("^el sistema mostrara un mensaje donde explica que no hay tickets registrados en el sistema para esa version del producto$")
    public void mostrarMensajeNoHayTicketsRegistradosParaEsaVersionProducto() {

    }

}
