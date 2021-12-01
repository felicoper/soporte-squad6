package Steps;
import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import cucumber.api.java.en.*;
import org.junit.jupiter.api.Assertions;

public class PasosCambioSeveridadTicket {
    Ticket ticket;

    @When("^el ingeniero de soporte cierre el ticket$")
    public void el_ingeniero_de_soporte_cierre_el_ticket(){
       // ticket =  new ticket();
        //ticket.cambiarEstado(Estado.CERRADO);
    }

    @Then("^indica que se cerro exitosamente$")
    public void indica_que_se_cerro_exitosamente(){

    }

    @When("^el ingeniero de soporte cambie la severidad del ticket$")
    public void el_ingeniero_de_soporte_cambie_la_severidad_del_ticket() {
       // ticket =  new ticket();
       // ticket.cambiarSeveridad(Severidad.S1);
    }

    @Then("^el sistema indica que no se puede cambiar la severidad del ticket$")
    public void el_sistema_indica_que_no_se_puede_cambiar_la_severidad_del_ticket() {

    }
    @Then("^el sistema registra el cambio de la severidad del ticket$")
    public void el_sistema_registra_el_cambio_de_la_severidad_del_ticket(){
    }

    @Then("^indica que se cambio la severidad exitosamente$")
    public void indica_que_se_cambio_la_severidad_exitosamente() {
    }
}
