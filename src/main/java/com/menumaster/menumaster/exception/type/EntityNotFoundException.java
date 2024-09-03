package com.menumaster.menumaster.exception.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String resource, String id) {
        super(String.format("%s with id %s not found", resource, id));
    }
}