package Steps;
import cucumber.api.java.en.*;


public class PasosCreacionTicket {
    Ticket ticket;


    @Given("los datos validos y obligatorios ingresados para un nuevo ticket")
    public Boolean validadorDatos(){
        return true;
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
    public void IndicarMensajeExitoYOpcionTarea(){
        System.out.println("Se creo el ticket exitosamente");
    }
}


//String nombreApellidoCliente, int legajoCliente, String titulo, String descripcion, String nombreProducto, int idProducto, String estadoTicket, String tipoTicket, int severidad