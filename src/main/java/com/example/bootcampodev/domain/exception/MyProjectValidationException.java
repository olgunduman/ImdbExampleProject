package com.example.bootcampodev.domain.exception;

import lombok.Getter;

@Getter
public class MyProjectValidationException extends RuntimeException {

    private final ExceptionType exceptionType;
    private String detail;


    public MyProjectValidationException(ExceptionType exceptionType, String detail) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
        this.detail = detail;
    }

    public MyProjectValidationException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
