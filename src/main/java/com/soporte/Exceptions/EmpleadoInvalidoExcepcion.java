package com.soporte.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class EmpleadoInvalidoExcepcion extends RuntimeException {
    public EmpleadoInvalidoExcepcion(String message) {
        super(message);
    }
}