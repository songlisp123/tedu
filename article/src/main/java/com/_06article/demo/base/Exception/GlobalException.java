package com._06article.demo.base.Exception;

import com._06article.demo.base.response.JsonResults;
import com._06article.demo.base.response.StatusCode;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResults doException(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        String message = methodArgumentNotValidException.getFieldError().getDefaultMessage();
        return new JsonResults(StatusCode.VALIDATED_ERROR,message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResults doException(ConstraintViolationException constraintViolationException)
    {
        String message = constraintViolationException.getMessage().split(": ")[1];
        return new JsonResults(StatusCode.VALIDATED_ERROR,message);
    }
}
