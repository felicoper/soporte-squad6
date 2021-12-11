package com.soporte.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class NoExisteElProducto extends RuntimeException{
    public NoExisteElProducto(String message) {
        super(message);
    }
    
}
