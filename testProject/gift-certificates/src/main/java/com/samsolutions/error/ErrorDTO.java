package com.samsolutions.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO implements Serializable {

    private int errorCode;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FieldValidationErrorDTO> fieldErrors = new ArrayList<>();

    public ErrorDTO() {}

    public ErrorDTO(int errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorDTO(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldValidationErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldValidationErrorDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public void addFieldError(FieldValidationErrorDTO fieldValidationError) {
        fieldErrors.add(fieldValidationError);
    }
}

