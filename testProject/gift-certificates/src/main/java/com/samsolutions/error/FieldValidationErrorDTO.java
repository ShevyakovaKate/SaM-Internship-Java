package com.samsolutions.error;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;

//TODO delete if не нужно
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FieldValidationErrorDTO implements Serializable {

    private String fieldName;
    private String message;

    public FieldValidationErrorDTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

}

