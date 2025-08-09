package com.az.edadi.model.exception;

import lombok.Data;

@Data
public class ExceptionResponse {

    private String timestamp;
    private int status;
    private Object errors;
}
