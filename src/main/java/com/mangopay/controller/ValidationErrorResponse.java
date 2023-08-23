package com.mangopay.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ValidationErrorResponse extends CustomErrorResponse {
    private List<ValidationError> validationErrors;

    public ValidationErrorResponse(String errorCode, String errorMessage, List<ValidationError> validationErrors) {
        super(errorCode, errorMessage);
        this.validationErrors = validationErrors;
    }
}
