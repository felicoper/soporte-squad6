package com.soporte.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class CamposFaltantesTicketExcepcion extends RuntimeException {
    public CamposFaltantesTicketExcepcion(String message) {
        super(message);
    }
}
