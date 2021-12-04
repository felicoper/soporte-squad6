package com.aninfo.integration.cucumber;

import com.soporte.SoporteApplication;
import com.soporte.repository.TicketRepository;
import com.soporte.service.ClientService;
import com.soporte.service.TicketService;
import com.soporte.service.EmpleadoService;
import com.soporte.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = SoporteApplication.class)
@WebAppConfiguration
@SpringBootTest(classes = SoporteApplication.class)
public class SoporteApplicationTest {
    @Autowired
    protected TicketService ticketService;

    @Autowired
    protected ClientService clientExternService;

    @Autowired
    protected EmpleadoService empleadoService;

    @Autowired
    protected ProductoService productService;

    @Autowired
    protected TicketRepository ticketRepository;
}