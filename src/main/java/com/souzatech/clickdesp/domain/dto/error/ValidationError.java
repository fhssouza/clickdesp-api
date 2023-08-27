package com.souzatech.clickdesp.domain.dto.error;

import lombok.*;

import java.util.ArrayList;
import java.util.List;



@Setter
public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public void addError(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }
}
