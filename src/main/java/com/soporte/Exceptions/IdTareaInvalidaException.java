package com.soporte.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class IdTareaInvalidaException extends RuntimeException {
    public IdTareaInvalidaException(String message) {
        super(message);
    }
}