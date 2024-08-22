package com.web.dpelos.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NotFoundException extends RuntimeException{
    private String id;
}
