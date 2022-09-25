package com.example.bootcampodev.adapter.rest.common;

import com.example.bootcampodev.domain.exception.DataNotFoundException;
import com.example.bootcampodev.domain.exception.ExceptionType;
import com.example.bootcampodev.domain.exception.MyProjectValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyProjectExceptionHandle {
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResponse handleDataNotFoundExcepiton(DataNotFoundException e){
        return new ExceptionResponse(e.getExceptionType(), e.getDetail());
    }

    @ExceptionHandler(MyProjectValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResponse handleValidationExcepiton(MyProjectValidationException e){
        return new ExceptionResponse(e.getExceptionType(), e.getDetail());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResponse handleDataNotFoundExcepiton(Exception e){
        return new ExceptionResponse(ExceptionType.GENERIC_EXCEPTION);
    }


}
