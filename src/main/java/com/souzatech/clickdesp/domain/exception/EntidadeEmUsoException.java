package com.souzatech.clickdesp.domain.exception;

public class EntidadeEmUsoException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public EntidadeEmUsoException(String message){
        super(message);
    }

}
