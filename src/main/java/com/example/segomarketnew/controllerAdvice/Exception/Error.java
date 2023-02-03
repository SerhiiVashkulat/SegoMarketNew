package com.example.segomarketnew.controllerAdvice.Exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private String message;
}
