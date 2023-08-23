package com.mangopay.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<ValidationError> validationErrors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationErrors.add(new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
            validationErrors.add(new ValidationError(objectError.getObjectName(), objectError.getDefaultMessage()));
        }
        ValidationErrorResponse errorResponse = new ValidationErrorResponse("VALIDATION_ERROR", "Validation failed",
                validationErrors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ValidationErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
        List<ValidationError> validationErrors = new ArrayList<>();
        validationErrors.add(new ValidationError("entity", ex.getLocalizedMessage()));
        ValidationErrorResponse errorResponse = new ValidationErrorResponse("VALIDATION_ERROR", "Validation failed",
                validationErrors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
