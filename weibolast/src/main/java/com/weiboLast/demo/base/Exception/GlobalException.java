package com.weiboLast.demo.base.Exception;

import com.weiboLast.demo.base.response.JsonResult;
import com.weiboLast.demo.base.response.StatusCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalException  {

    @ExceptionHandler(IllegalAccessError.class)
    public JsonResult doHandlerException(IllegalAccessError illegalAccessError)
    {
        String message = illegalAccessError.getMessage();
        return new JsonResult(StatusCode.NOT_LOGIN,message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult doHandlerException(
            ConstraintViolationException constraintViolationException)
    {
        String wrongMessage = constraintViolationException.getMessage().split(": ")[1];
        return new JsonResult(StatusCode.VALIDATION_ERROR,wrongMessage);
    }
}
