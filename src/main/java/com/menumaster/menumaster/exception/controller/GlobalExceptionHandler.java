package com.menumaster.menumaster.exception.controller;

import com.menumaster.menumaster.exception.config.ErrorDescription;
import com.menumaster.menumaster.exception.type.DuplicateKeyException;
import com.menumaster.menumaster.exception.type.EntityNotFoundException;
import com.menumaster.menumaster.exception.type.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDescription> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDescription> handleValidationException(ValidationException ex) {
        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorDescription> handleDuplicateKeyException(DuplicateKeyException ex) {
        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // Novo handler para capturar MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDescription>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorDescription> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            ErrorDescription errorDescription = new ErrorDescription(
                    HttpStatus.BAD_REQUEST.value(),
                    error.getField() + ": " + error.getDefaultMessage()
            );
            errors.add(errorDescription);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Handler para capturar DataIntegrityViolationException causada por SQLIntegrityConstraintViolationException
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDescription> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof SQLIntegrityConstraintViolationException) {
            SQLIntegrityConstraintViolationException sqlException = (SQLIntegrityConstraintViolationException) ex.getCause();
            ErrorDescription errorResponse = new ErrorDescription(
                    HttpStatus.CONFLICT.value(),  // 409 CONFLICT
                    "Duplicate entry: " + sqlException.getMessage()
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        ErrorDescription errorResponse = new ErrorDescription(
                HttpStatus.BAD_REQUEST.value(),
                "Data integrity violation: " + ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
