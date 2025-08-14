package com.gufelipe.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class ClienteJaExistenteException extends ApplicationException{

    public ClienteJaExistenteException(String message, String code) {
        super(message, code, HttpStatus.PRECONDITION_FAILED);
    }
}
