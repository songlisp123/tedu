package com.eguser1.demo.base.Exception;

import com.eguser1.demo.base.response.JsonResults;
import com.eguser1.demo.base.response.StatusCode;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler
    public JsonResults handlerException(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        String wrongMessage = methodArgumentNotValidException.getFieldError().getDefaultMessage();
        return new JsonResults(StatusCode.OPERATION_FAILED,wrongMessage);
    }
    @ExceptionHandler
    public JsonResults handlerException(ConstraintViolationException constraintViolationException)
    {
        String wrongMessage = constraintViolationException.getMessage().split(": ")[1];
        return new JsonResults(StatusCode.OPERATION_FAILED,wrongMessage);
    }
}
