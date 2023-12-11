package com.souzatech.clickdesp.infrastructure.exception;

public class StatusOrdemServicoException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public StatusOrdemServicoException(String message){
        super(message);
    }
}

