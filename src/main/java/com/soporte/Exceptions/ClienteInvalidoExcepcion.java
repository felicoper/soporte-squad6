package com.soporte.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class ClienteInvalidoExcepcion extends RuntimeException {
    public ClienteInvalidoExcepcion(String message) {
        super(message);
    }
}
