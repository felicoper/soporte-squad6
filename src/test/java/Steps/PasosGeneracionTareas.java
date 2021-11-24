package Steps;
import cucumber.api.java.en.*;

public class PasosGeneracionTareas {

    @Given("un ticket no cerrado, un proyecto existente, y los datos obligatorios para un ticket")
    public void ticketNoCerradoProyectoExistenteYDatosObligatorios(){

    }
    @Given("un ticket no cerrado, un proyecto no existente, y los datos obligatorios para un ticket")
    public void ticketNoCerradoProyectoInexistenteYDatosObligatorios(){

    }
    @Given("un ticket cerrado, un proyecto existente, y los datos obligatorios para un ticket")
    public void ticketCerradoProyectoExistenteYDatosObligatorios(){

    }
    @When("el ingeniero de soporte cree para un ticket la nueva tarea en un proyecto")
    public void crearNuevaTareaEnProyecto(){

    }
    @Then("el sistema registrará sobre el ticket una nueva tarea, cambiando la persona asignada al ticket por el responsable de la tarea")
    public void registrarNuevaTareaSobreTicket(){

    }
    @Then("el sistema no creará la tarea")
    public void noCrearTarea(){

    }
    @Then("solicitará que ingrese el nombre de un proyecto existente")
    public void solicitarIngresoDeNombreDeProyectoExistente(){

    }
    @Then("informará que no es posible crear una tarea a un ticket cerrado")
    public void informarQueNoSePuedeCrearTareaATicketCerrado(){

    }
}
