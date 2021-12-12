package com.soporte.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class NoExistenTickets extends RuntimeException{
    public NoExistenTickets(String message) {
        super(message);
    }
    
}
