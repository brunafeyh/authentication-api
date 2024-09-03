package com.menumaster.menumaster.exception.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends RuntimeException {
    private String field;
    private String message;
    private String details;

    public ValidationException(String field, String message, String details) {
        super(message);
        this.field = field;
        this.message = message;
        this.details = details;
    }
}