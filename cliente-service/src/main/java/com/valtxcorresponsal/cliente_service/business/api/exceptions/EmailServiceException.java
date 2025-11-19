package com.valtxcorresponsal.cliente_service.business.api.exceptions;

public class EmailServiceException extends RuntimeException{
    public EmailServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
