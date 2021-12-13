package com.aninfo.integration.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import com.soporte.model.Cliente;
import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;
import com.soporte.model.VersionProducto;
import com.soporte.repository.VersionProductoRepository;
import com.soporte.service.ProductoService;
import com.soporte.model.Empleado;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.bytebuddy.agent.builder.AgentBuilder.Listener;

import org.hibernate.mapping.Collection;
import org.junit.Assert;

import antlr.Version;

public class ConsultaVersionTest extends SoporteApplicationTest{
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;
    Integer id_producto_consultada;
    ArrayList<VersionProducto> versiones_producto = new ArrayList<VersionProducto>();

    Integer idVersionProductoValido;
    Integer idVersionProductoInvalido;

    @Before
    public void setup() throws ParseException {
        this.setup_all();
    }
  

    private Integer obtenerLegajoEmpleadoInexistente() {
        int i = clientes_validos.size();
        while (i > 0) {
            i++;
            if (!empleados_validos.contains(i)) {
                return i;
            }
        }
        return null;
    }

    private Integer obtenerLegajoClienteInexistente() {
        int i = clientes_validos.size();
        while (i > 0) {
            i++;
            if (!clientes_validos.contains(i)) {
                return i;
            }
        }
        return null;
    }

    @Given("^Hay versiones de un producto registrados en sistema$")
    public void versionesDeUnProductoRegristradosEnSistema() {

        Integer idVersionProductoValido = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);
     
        VersionProducto version = productService.getVersionProducto(idVersionProductoValido);
        id_producto_consultada = version.getProducto().getId();
    }

    @When("^El ingeniero de soporte consulte las versiones de un producto$")
    public  void intentoConsultarVersiones() {
        try {
            versiones_producto = (ArrayList<VersionProducto>)productService.getVersionesOFProducto(id_producto_consultada);
        } catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^El sistema muestra el conjunto de todas las versiones asignadas al producto$")
    public void sistema_muestra_versiones(){
        Assert.assertNotNull(versiones_producto);
    }

    @Given("^Hay un producto inexistente$")
    public void producto_inexistente(){
        id_producto_consultada = 200;
    }

    @Then("^El sistema muestra un mensaje donde explica que no existe ese producto en la empresa$")
    public void el_sistema_muestra_un_mensaje_donde_explica_que_no_existe_ese_producto_en_la_empresa(){
        assertEquals(excepcionRecibida.getMessage(),"El producto no existe");
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }

}