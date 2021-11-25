package Steps;
import com.soporte.model.Cliente;
import com.soporte.model.PersonaAsignada;
import cucumber.api.java.en.*;
import com.soporte.model.Ticket;

public class PasosCreacionTicket {
    Ticket ticket;
    Cliente clienteValido;
    PersonaAsignada personsaAsignadaValida;


    @Given("los datos validos y obligatorios ingresados para un nuevo ticket")
    public Boolean validadorDatos(){


        return true;
    }
    @Given("se ingresan los datos obligatorios pero con informacion de cliente inexistente")
    public void ingresoClienteInexistente(){

    }
    @Given("se ingresan los datos obligatorios pero con informacion de persona asignada inexistente")
    public void ingresoPersonaInexistente(){

    }
    @Given("se ingresan los datos obligatorios pero con informacion de un producto inexistente")
    public void ingresoProductoInexistente(){

    }
    @Given("se ingresan los datos obligatorios pero con el numero de legajo del cliente menor a cero")
    public void ingresoNumeroLegajoClienteMenorACero(){

    }
    @Given("se ingresan los datos obligatorios pero con el numero de legajo de la persona asignada menor a cero")
    public void ingresoNumeroLegajoPersonaAsignadaMenorACero(){

    }
    @Given("se ingresan los datos obligatorios pero con el id del producto menor a cero")
    public void ingresoIdProductoMenorACero(){

    }
    @Given("se ingresaron datos para el ticket pero con algun dato obligatorio faltante")
    public void ingresoConDatoObligatorioFaltante(){

    }

    @When("el ingeniero de soporte crea un nuevo ticket con los datos")
    public void intentoCrearTicket(){
        if(validadorDatos()){
            //this.ticket = new Ticket();
        }
    }
    @Then("el sistema registrara el nuevo ticket")
    public void registroTicket(){

    }
    @Then("indica un mensaje de exito con identificador y fecha de creacion")
    public void indicarMensajeExitoYOpcionTarea(){
        System.out.println("Se creo el ticket exitosamente");
    }

    @Then("dara opcion de crearle una nueva tarea")
    public void opcionCrearTarea(){};

    @Then("el sistema no registrara el nuevo ticket")
    public void noRegistrarTicket(){

    }
    @Then("solicitara que ingrese los datos de un cliente existente en la empresa")
    public void solicitudClienteExistente(){ }

    @Then("solicitara que ingrese los datos de una persona existente en la empresa")
    public void solicitudPersonaExistente(){ }

    @Then("solicitara que ingrese los datos de un producto existente en la empresa")
    public void solicitudProductoExistente(){ }

    @Then("solicitara que reingrese el legajo del cliente correctamente")
    public void solicitudLegajoClienteMayorACero(){

    }
    @Then("solicitara que reingrese el legajo de la persona asignada correctamente")
    public void solicitudLegajoPersonaAsignadaMayorACero(){

    }
    @Then("solicitara que reingrese el id del producto correctamente")
    public void solicitudIdProductoMayorACero(){

    }
    @Then("solicitara que ingrese los datos obligatorios")
    public void solicitudDatosObligatorios(){

    }
}
