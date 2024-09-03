package com.menumaster.menumaster.exception.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateKeyException extends RuntimeException{
    private String message;
    private String details;

    public DuplicateKeyException(String message, String details) {
        super(message);
        this.message = message;
        this.details = details;
    }
}