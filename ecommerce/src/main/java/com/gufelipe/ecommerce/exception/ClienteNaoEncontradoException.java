package com.gufelipe.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class ClienteNaoEncontradoException extends ApplicationException{

    public ClienteNaoEncontradoException(String message, String code) {
        super(message, code, HttpStatus.NOT_FOUND);
    }
}