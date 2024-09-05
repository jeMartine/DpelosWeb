package com.web.dpelos.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
