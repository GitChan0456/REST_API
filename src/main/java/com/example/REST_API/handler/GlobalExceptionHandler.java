package com.example.REST_API.handler;

import com.example.REST_API.Dto.ErrorResponseDto;
import com.example.REST_API.exception.BusinessException;
import com.example.REST_API.exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponseDto handleNotFound(NotFoundException exception) {

        return ErrorResponseDto.of(exception.getCode());
    }

    @ExceptionHandler(BusinessException.class)
    public ErrorResponseDto handleBusiness(
            BusinessException exception) {

        return ErrorResponseDto.of(exception.getCode());
    }
}