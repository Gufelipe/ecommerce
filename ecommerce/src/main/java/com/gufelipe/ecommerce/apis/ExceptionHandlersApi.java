package com.gufelipe.ecommerce.apis;

import com.gufelipe.ecommerce.exception.ApiError;
import com.gufelipe.ecommerce.exception.ApplicationException;
import com.gufelipe.ecommerce.exception.ProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlersApi {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ApiError HandlerProdutoNaoEncontradoException(ProdutoNaoEncontradoException ex) {
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
    public ResponseEntity<ApiError> handlerProdutoNotFoundException(Exception ex) {
        ApiError apiError = new ApiError(ex.getMessage(), "INDEFINIDO");

        return ResponseEntity
                .internalServerError()
                .body(apiError);
    }
}
