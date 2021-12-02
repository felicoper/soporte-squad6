package com.aninfo.integration.cucumber;

import com.soporte.SoporteApplication;
import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.model.Ticket;
import com.soporte.repository.TicketRepository;
import com.soporte.service.ClientExternService;
import com.soporte.service.TicketService;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = SoporteApplication.class)
@WebAppConfiguration
public class SoporteServiceTest {

    @InjectMocks
    protected TicketService ticketService;

    @Mock
    protected ClientExternService clientExternService;

    @Mock
    protected TicketRepository ticketRepository;

    Ticket createTicket(Ticket ticketParseado) throws ClienteInvalidoExcepcion {
        return ticketService.createTicket(ticketParseado, clientExternService);
    }

}
