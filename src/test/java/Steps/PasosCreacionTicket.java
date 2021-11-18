package Steps;
import cucumber.api.java.en.*;


public class PasosCreacionTicket {
    Ticket ticket;


    @Given("los datos validos y obligatorios ingresados para un nuevo ticket")
    public Boolean validadorDatos(){
        return true;
    }
    @Given("se ingresan los datos obligatorios pero con informacion de cliente inexistente")
    public void ingresoInfoInexistente(){

    }
    @Given("se ingresan los datos obligatorios pero con el (.*) menor a cero")
    public void ingresoDatoMenorACero(){

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
    @Then("indica un mensaje de exito con identificador, fecha de creacion y opcion de crearle una nueva tarea al ticket")
    public void indicarMensajeExitoYOpcionTarea(){
        System.out.println("Se creo el ticket exitosamente");
    }

    @Then("el sistema no registrara el nuevo ticket")
    public void noRegistrarTicket(){

    }
    @Then("solicitara que ingrese los datos de un (.*) existente en la empresa")
    public void solicitudDatoExistente(){

    }
    @Then("solicitara que reingrese el (.*) correctamente")
    public void solicitudDatoMayorACero(){

    }
    @Then("solicitara que ingrese los datos obligatorios")
    public void solicitudDatosObligatorios(){

    }
}


//String nombreApellidoCliente, int legajoCliente, String titulo, String descripcion, String nombreProducto, int idProducto, String estadoTicket, String tipoTicket, int severidad