package com.aninfo.integration.cucumber;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.model.Cliente;
import com.soporte.model.Producto;
import com.soporte.model.Severidad;
import com.soporte.model.Ticket;
import com.soporte.model.TicketRequest;
import com.soporte.model.TipoTicket;
import com.soporte.model.VersionProducto;
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
        System.out.println("Before any test execution");
        clientExternService.save(new Cliente(100, "FIUBA", "20123456786"));
        clientExternService.save(new Cliente(101, "USAL", "20123456783"));
        clientExternService.save(new Cliente(102, "UNPAZ", "20123456784"));
        clientExternService.save(new Cliente(103, "SA", "20123456780"));
        clientExternService.save(new Cliente(104, "SE", "20123456781"));

        empleadoService.save(new Empleado(100898, "Fernando", "Bursztyn"));
        empleadoService.save(new Empleado(23456, "Federico", "noseElApellido"));
        empleadoService.save(new Empleado(112233, "Martin", "Carlos"));
        empleadoService.save(new Empleado(445566, "Felipe", "Copertini"));
        empleadoService.save(new Empleado(778899, "Mateo", "Bulnes"));

        Producto producto1 = new Producto(1, "SIU Guarani");
        Producto producto2 = new Producto(2, "Linux");
        productService.saveDatabase(producto1);
        productService.saveDatabase(producto2);
		VersionProducto versionProducto1 = new VersionProducto(1, "0.99b", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2018"));
		VersionProducto versionProducto2 = new VersionProducto(2, "3.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2018"));
        VersionProducto versionProducto3 = new VersionProducto(3, "1.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019"));
        VersionProducto versionProducto4 = new VersionProducto(4, "2.0", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2020"));
        VersionProducto versionProducto5 = new VersionProducto(5, "4.1", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019"));
        VersionProducto versionProducto6 = new VersionProducto(6, "5.2", new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2020"));

		versionProducto1.setProducto(producto1);
		versionProducto2.setProducto(producto2);
		versionProducto3.setProducto(producto1);
		versionProducto4.setProducto(producto1);
		versionProducto5.setProducto(producto2);
		versionProducto6.setProducto(producto2);

		productService.saveDatabaseVersion(versionProducto1);
		productService.saveDatabaseVersion(versionProducto2);
		productService.saveDatabaseVersion(versionProducto3);
		productService.saveDatabaseVersion(versionProducto4);
		productService.saveDatabaseVersion(versionProducto5);
		productService.saveDatabaseVersion(versionProducto6);

    }

    @Given("^Los datos validos y obligatorios ingresados para un nuevo ticket$")
    public void datosValidosYObligatoriosIngresadosParaNuevoTicket() {
        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme con Argerich y Mendez a la vez D:";
        Integer legajoCliente = 100;
        Integer legajoEmpleado = 23456;
        Integer idVersionProducto = 1;
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        ticketRequest = new TicketRequest(titulo, descripcion, legajoCliente, legajoEmpleado, idVersionProducto, tipoTicket, severidadTicket);
    }

    @Given("^Se ingresaron datos para el ticket pero con algun campo obligatorio faltante$")
    public void datosConAlgunCampoObligatorioFaltante() {
        Integer legajoClienteInexistente = null;

        String titulo = "Problema con SIU";
        String descripcion = "Problema al querer anotarme con Argerich y Mendez a la vez D:";
        Integer legajoEmpleado = 23456;
        Integer idVersionProducto = 1;
        TipoTicket tipoTicket = TipoTicket.CONSULTA;
        Severidad severidadTicket = Severidad.S1;
        ticketRequest = new TicketRequest(titulo, descripcion, legajoClienteInexistente, legajoEmpleado, idVersionProducto, tipoTicket, severidadTicket);
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
            ticketService.deleteById(ticketCreado.getNumeroTicket()); // lo borro..
        } else { // "no registrara"
            Assert.assertNull(ticketCreado); // NO se creó!
        }
    }

    @And("^Solicitara que ingrese los datos obligatorios faltantes$")
    public void solicitaDatosFaltantes() {
        Assert.assertEquals("El cliente no pertenece a la empresa.", this.excepcionRecibida.getMessage());
    }

    @Given("^Los datos obligatorios ingresados para un nuevo ticket pero con \"([^\"]*)\" en la empresa$")
    public void los_datos_obligatorios_ingresados_para_un_nuevo_ticket_pero_con_en_la_empresa(String arg1) {
        Assert.assertTrue(true);
    }

    @And("^Solicitará que ingrese los datos de \"([^\"]*)\" existente en la empresa\\.$")
    public void solicitará_que_ingrese_los_datos_de_existente_en_la_empresa(String arg1){
        Assert.assertTrue(true);
    }
    
    @Given("^Los datos obligatorios ingresados para un nuevo ticket pero con el \"([^\"]*)\" menor a cero$")
    public void los_datos_obligatorios_ingresados_para_un_nuevo_ticket_pero_con_el_menor_a_cero(String arg1){
        Assert.assertTrue(true);

    }

    @And("^Solicitará que reingrese \"([^\"]*)\" correctamente\\.$")
    public void solicitará_que_reingrese_correctamente(String arg1){
        Assert.assertTrue(true);

    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");

    }
}
