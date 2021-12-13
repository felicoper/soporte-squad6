package com.aninfo.integration.cucumber;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import com.soporte.model.Cliente;
import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;
import com.soporte.model.Empleado;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class CreacionTest extends SoporteApplicationTest{
    TicketRequest ticketRequest;
    Ticket ticketCreado;
    RuntimeException excepcionRecibida;
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

    @Given("^Los datos validos y obligatorios ingresados para un nuevo ticket$")
    public void datosValidosYObligatoriosIngresadosParaNuevoTicket() {
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme D:";
        Integer legajoClienteValido = 1 + new Random().nextInt(clientes_validos.size() - 1);
        Integer legajoEmpleadoValido = 1 + new Random().nextInt(empleados_validos.size() - 1);
        Integer idVersionProductoValido = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        ticketRequest = new TicketRequest(titulo, descripcion, legajoClienteValido, legajoEmpleadoValido, idVersionProductoValido, tipoTicket, severidadTicket);
    }

    @Given("^Se ingresaron datos para el ticket pero con algun campo obligatorio faltante$")
    public void datosConAlgunCampoObligatorioFaltante() {
        Integer legajoClienteInexistente = this.obtenerLegajoClienteInexistente();

        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme D:";
        Integer legajoEmpleadoValido = 1 + new Random().nextInt(empleados_validos.size() - 1);
        Integer idVersionProductoValido = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        ticketRequest = new TicketRequest(titulo, descripcion, legajoClienteInexistente, legajoEmpleadoValido, idVersionProductoValido, tipoTicket, severidadTicket);
    }

    @When("^El ingeniero de soporte crea un nuevo ticket con los datos$")
    public void intentoCrearTicket() {
        try {
            ticketCreado = ticketService.createTicket(ticketRequest);
        } catch (RuntimeException excepcionRecibida) {
            this.excepcionRecibida = excepcionRecibida;
        }
    }

    @Then("^El sistema \"([^\"]*)\" el nuevo ticket$")
    public void registroTicket(String registraONo) {
        if (registraONo.equals("registrara")) {
            Assert.assertNotNull(ticketCreado); // Se creó perfecto!
            ticketService.deleteById(ticketCreado.getNumeroTicket()); // lo borro despues de crear!..
        } else { // "no registrara"
            Assert.assertNull(ticketCreado); // NO se creó!
        }
    }

    @And("^Solicitara que ingrese los datos obligatorios faltantes$")
    public void solicitaDatosFaltantes() {
        Assert.assertEquals("El cliente no pertenece a la empresa.", this.excepcionRecibida.getMessage());
    }

    @Given("^Los datos obligatorios ingresados para un nuevo ticket pero con \"([^\"]*)\" en la empresa$")
    public void los_datos_obligatorios_ingresados_para_un_nuevo_ticket_pero_con_en_la_empresa(String arg) {
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme con Argerich y Mendez a la vez D:";
        Integer legajoCliente = 0;
        Integer legajoEmpleado = 0;
        Integer idVersionProducto = 0;
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;

        switch (arg) {
            case "información de cliente inexistente": {
                legajoCliente = this.obtenerLegajoClienteInexistente();
                legajoEmpleado = 1 + new Random().nextInt(empleados_validos.size() - 1);
                idVersionProducto = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);
                break;
            }
            case "una persona asignada inexistente": {
                legajoCliente = 1 + new Random().nextInt(clientes_validos.size() - 1);
                legajoEmpleado = this.obtenerLegajoEmpleadoInexistente();
                idVersionProducto = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);
                break;
            }
            case "un producto inexistente": {
                legajoCliente = 1 + new Random().nextInt(clientes_validos.size() - 1);
                legajoEmpleado = 1 + new Random().nextInt(empleados_validos.size() - 1);
                idVersionProducto = 100;
                break;
            }
            default:
                Assert.fail("No se esperaba esta excepción");
        }
        ticketRequest = new TicketRequest(titulo, descripcion, legajoCliente, legajoEmpleado, idVersionProducto, tipoTicket, severidadTicket);
    }

    @And("^Solicitará que ingrese los datos de \"([^\"]*)\" existente en la empresa\\.$")
    public void solicitará_que_ingrese_los_datos_de_existente_en_la_empresa(String arg){
        switch (arg) {
            case "un cliente": {
                Assert.assertEquals("El cliente no pertenece a la empresa.", this.excepcionRecibida.getMessage());
                break;
            }
            case "una persona": {
                Assert.assertEquals("El empleado no pertenece a la empresa.", this.excepcionRecibida.getMessage());
                break;
            }
            case "un producto": {
                Assert.assertEquals("No se encontró la version producto", this.excepcionRecibida.getMessage());
                break;
            }
            default:
                Assert.fail("No se esperaba esta excepción");
        }
    }

    @Given("^Los datos obligatorios ingresados para un nuevo ticket pero con el \"([^\"]*)\" menor a cero$")
    public void los_datos_obligatorios_ingresados_para_un_nuevo_ticket_pero_con_el_menor_a_cero(String arg){
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme con Argerich y Mendez a la vez D:";
        Integer legajoCliente = 0;
        Integer legajoEmpleado = 0;
        Integer idVersionProducto = 0;
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;

        switch (arg) {
            case "número de legajo del cliente": {
                legajoCliente = -10;
                legajoEmpleado = 1 + new Random().nextInt(empleados_validos.size() - 1);
                idVersionProducto = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);
                break;
            }
            case "número de legajo de la persona asignada": {
                legajoCliente = 1 + new Random().nextInt(clientes_validos.size() - 1);
                legajoEmpleado = -10;
                idVersionProducto = 1 +  new Random().nextInt(productService.getVersionesProductos().size() - 1);
                break;
            }
            case "id del producto": {
                legajoCliente = 1 + new Random().nextInt(clientes_validos.size() - 1);
                legajoEmpleado = 1 + new Random().nextInt(empleados_validos.size() - 1);
                idVersionProducto = -10;
                break;
            }
            default:
                Assert.fail("No se esperaba esta excepción");
        }
        ticketRequest = new TicketRequest(titulo, descripcion, legajoCliente, legajoEmpleado, idVersionProducto, tipoTicket, severidadTicket);
    }

    @And("^Solicitará que reingrese \"([^\"]*)\" correctamente\\.$")
    public void solicitará_que_reingrese_correctamente(String arg){
        switch (arg) {
            case "el legajo del cliente": {
                Assert.assertEquals("El id del cliente no puede ser negativo", this.excepcionRecibida.getMessage());
                break;
            }
            case "el legajo de la persona asignada": {
                Assert.assertEquals("El legajo no puede ser negativo", this.excepcionRecibida.getMessage());
                break;
            }
            case "el id del producto": {
                Assert.assertEquals("El id de la version de producto no puede ser negativo", this.excepcionRecibida.getMessage());
                break;
            }
            default:
                Assert.fail("No se esperaba esta excepción");
        }
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
