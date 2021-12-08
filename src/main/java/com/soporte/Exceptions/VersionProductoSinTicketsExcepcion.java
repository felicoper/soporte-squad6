package com.soporte.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class VersionProductoSinTicketsExcepcion extends RuntimeException {
    public VersionProductoSinTicketsExcepcion(String message) {
        super(message);
    }
}
