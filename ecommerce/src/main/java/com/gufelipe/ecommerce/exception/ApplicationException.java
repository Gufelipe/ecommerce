package com.gufelipe.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException{

    private String code;
    private HttpStatus httpStatus;

    public ApplicationException(String message, String code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
