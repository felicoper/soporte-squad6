package com.soporte.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class VersionProductoInexistente extends RuntimeException {
    public VersionProductoInexistente(String message) {
        super(message);
    }
}
