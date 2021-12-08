package com.soporte.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class TicketInexistenteExcepcion extends RuntimeException {
    public TicketInexistenteExcepcion(String message) {
        super(message);
    }
}
