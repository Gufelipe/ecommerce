package com.gufelipe.ecommerce.apis;

import com.gufelipe.ecommerce.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlersApi {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ApiError HandlerProdutoNaoEncontradoException(ProdutoNaoEncontradoException ex) {
        return new ApiError(
                ex.getMessage(),
                ex.getCode()
        );
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ApiError HandlerClienteNaoEncontradoException(ProdutoNaoEncontradoException ex) {
        return new ApiError(
                ex.getMessage(),
                ex.getCode()
        );
    }

    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(ClienteJaExistenteException.class)
    public ApiError HandlerClienteJaExistenteException(ClienteJaExistenteException ex){
        return new ApiError(
                ex.getMessage(),
                ex.getCode()
        );
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiError> handlerApplicationException(ApplicationException ex){
        ApiError apiError = new ApiError(
                ex.getMessage(),
                ex.getCode()
        );
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handlerException(Exception ex) {
        ApiError apiError = new ApiError(ex.getMessage(), "INDEFINIDO");

        return ResponseEntity
                .internalServerError()
                .body(apiError);
    }
}
