package com._01web.demo.base.exception;

import com._01web.demo.base.response.JsonResults;
import com._01web.demo.base.response.StatusCode;
import com._01web.demo.user.service.serviceException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class globalException {

    /*
    自定义业务端异常：
    1、用户不存在
    2、用户访问权限不够的内容
    * */

    @ExceptionHandler(serviceException.class)
    public JsonResults doHandlerserviceException(serviceException e) {
        String message = e.getStatusCode().getMessage();
        log.info("异常：{}",message);
        return new JsonResults(e.getStatusCode());
    }

    /** 异常处理方法1:演示全局异常处理器的使用方法; */
    @ExceptionHandler
    public JsonResults doHandleIllegalArgumentException(IllegalArgumentException ex){
        String data = ex.getMessage();
        log.error("IllegalArgumentException:" + data);
        //{"code":3000,"message":"操作失败","data":"车辆编号不能小于0"}
        return JsonResults.fail(data);
    }

    //dto类型参数验证异常捕获器
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResults doHandlerMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        String error = ex.getFieldError().getDefaultMessage();
        log.warn("错误：{}",error);
        return new JsonResults(StatusCode.VALIDATE_ERROR);
    }

    //路径参数检验异常捕获器
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResults doHandleConstraintViolationException(ConstraintViolationException ex)
    {
        String error = ex.getMessage();
        log.warn("错误：{}",error);
        return new JsonResults(StatusCode.VALIDATE_ERROR);
    }

    //父类runtimeException异常捕获器
    @ExceptionHandler(RuntimeException.class)

    public JsonResults doHandlerRuntimeException(RuntimeException ex) {
        String error = ex.getMessage();
        log.warn("异常：{}",error);
        return new JsonResults(StatusCode.OPERATION_FAILED);
    }


}
