package com.cloudCar.demo.base.exception;


import com.cloudCar.demo.base.response.JsonResult;
import com.cloudCar.demo.base.response.StatussCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * ControllerAdvice注解:
 *   1.标识该类为全局异常处理器的类;
 *   2.当控制器抛出异常时,框架会检查是否定义了全局异常处理器;
 *
 * RestControllerAdvice注解:
 *   1.组合注解,等价于 ControllerAdvice + ResponseBody;
 *   2.添加该注解后,所有的异常处理方法上就无需再添加 ResponseBody 注解了;
 * 异常处理的顺序:
 *   1.当控制器抛出异常后,框架先检查是否定义了全局异常处理器;
 *   2.如果未定义,则使用默认的异常处理机制[不友好];
 *   3.如果定义了,则到该异常处理器中定位对应的异常处理方法;
 *   4.如果找到了对应的异常处理方法,则由该方法进行异常处理;
 *   5.如果没有找到对应的异常处理方法,则使用该异常父类异常的异常处理方法;
 *   6.如果父类异常没有对应的异常处理方法,则使用能处理所有异常的异常处理方法;
 *   7.如果Throwable异常处理方法也未定义,则使用默认的异常处理机制[不友好];
 */
//@ControllerAdvice
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * ExceptionHandler注解:标识当前方法为异常处理方法,需要指定异常的类型;
     */
    private static final Logger logger = Logger.getLogger("car");
    @ExceptionHandler(IllegalArgumentException.class)
    public JsonResult doHandleIllegalArgumentException(IllegalArgumentException ex){
        //获取异常提示消息: throw new IllegalArgumentException("车辆id不能小于0");
        String data = ex.getMessage();
        //打印日志
        log.error("IllegalArgumentException:" + data);
        //{"state":3000, "message":"操作失败", "data":"车辆id不能小于0"}
        return new JsonResult(StatussCode.OPERATION_FAILED, data);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public JsonResult doHandleRuntimeException(RuntimeException ex){
//        String data = ex.getMessage();
//        log.error("RuntimeException:" + data);
//        return new JsonResult(StatussCode.OPERATION_FAILED, data);
//    }

    /**
     * 能够处理所有异常的异常处理方法;
     */
//    @ExceptionHandler(Throwable.class)
//    public JsonResult doHandleThrowable(Throwable ex){
//        String data = ex.getMessage();
//        log.error("Throwable:" + data);
//        return new JsonResult(StatussCode.THROWABLE_ERROR, data);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult doHandleRuntimeException(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        Map<FieldError,String> message = new HashMap<>();
        List<FieldError> fieldErrors = methodArgumentNotValidException.getFieldErrors();
        fieldErrors.forEach(e->{
            logger.warning(e.getDefaultMessage());
            message.put(e,e.getDefaultMessage());
        });
        return new JsonResult(StatussCode.VALIDATED_ERROR,message);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult doHandleRuntimeException(ConstraintViolationException ConstraintViolationException)
    {
        String message = ConstraintViolationException.getMessage().split(": ")[1];
        logger.warning("报错："+message);
        return new JsonResult(StatussCode.VALIDATED_ERROR,message);
    }
}









