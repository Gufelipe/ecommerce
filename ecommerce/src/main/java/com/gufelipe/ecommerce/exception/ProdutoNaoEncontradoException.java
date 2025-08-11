package com.gufelipe.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class ProdutoNaoEncontradoException extends ApplicationException {

    public ProdutoNaoEncontradoException(String message, String code) {
        super(message, code, HttpStatus.NOT_FOUND);
    }
}
