package com.weibo.demo.base.Exception;

import com.weibo.demo.base.response.JsonResults;
import com.weibo.demo.base.response.StatusCode;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResults doHandlerException(MethodArgumentNotValidException e) {
        String message = e.getFieldError().getDefaultMessage();
        return new JsonResults(StatusCode.VALIDATED_ERROR,message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResults doHandlerException(ConstraintViolationException e)
    {
        String message = e.getMessage().split(": ")[1];
        return new JsonResults(StatusCode.VALIDATED_ERROR,message);
    }
}
