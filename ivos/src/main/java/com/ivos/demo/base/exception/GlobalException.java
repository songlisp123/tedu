package com.ivos.demo.base.exception;

import com.ivos.demo.base.response.JsonResult;
import com.ivos.demo.base.response.StatusCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalException {

    /*自定义异常处理方式*/
    @ExceptionHandler(serviceException.class)
    public JsonResult doHandleIllegalArgumentException(serviceException ex) {
        log.error(ex.getStatusCode().getMessage());
        return new JsonResult(ex.getStatusCode());
    }

    /** 异常处理方法1:演示全局异常处理器的使用方法; */
    @ExceptionHandler
    public JsonResult doHandleIllegalArgumentException(IllegalArgumentException ex){
        String data = ex.getMessage();
        log.error("IllegalArgumentException:" + data);
        //{"code":3000,"message":"操作失败","data":"车辆编号不能小于0"}
        return JsonResult.fail(data);
    }

    /** 异常处理方法2:处理封装类【POJO】参数合法性校验失败的场景;MethodArgumentNotValidException */
    /*
        1. 捕获异常：当封装类参数（例如在 Controller方法上有使用了@Valid注解标注的Java Bean作为参数）校验失败时，会抛出 MethodArgumentNotValidException 异常
        2. 获取错误信息：使用 ex.getFieldError().getDefaultMessage() 获取异常消息
        3. 记录错误日志
        4. 返回结果：将错误消息message封装到响应对象 JsonResult 的data中，返回给前端
    */
    @ExceptionHandler
    public JsonResult doHandleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        //封装类参数异常提示消息: ex.getFieldError().getDefaultMessage();
        String data = ex.getFieldError().getDefaultMessage();
        log.error("MethodArgumentNotValidException:" + data);
        return new JsonResult(StatusCode.VALIDATE_ERROR, data);
    }

    /** 异常处理方法3: 非封装参数数据合法性校验异常处理方法; ConstraintViolationException*/
    /*
        1. 捕获异常：当非封装类参数（例如在 Controller 方法参数上直接使用 @NotBlank、@Size 等注解）校验失败时，会抛出 ConstraintViolationException 异常
        2. 获取错误信息：使用 ex.getMessage() 获取异常消息，消息格式 username: 用户名不能为空
        3. 拆分字符串：使用 split(":")` 按冒号将字符串拆分成数组，得到类似 ["username", " 用户名不能为空"]
        4. 提取具体错误提示：取出数组中第2个元素，也就是错误描述部分，再通过 .trim() 去除前后空格
        5. 记录错误日志
        6. 返回结果：将错误消息message封装到响应对象 JsonResult 的data中，返回给前端
    */
    @ExceptionHandler
    public JsonResult doHandleConstraintViolationException(ConstraintViolationException ex){
        String data = ex.getMessage().split(":")[1].trim();
        log.error("ConstraintViolationException:" + data);
        return new JsonResult(StatusCode.VALIDATE_ERROR, data);
    }

    /** 异常处理方法4: 演示父类异常的处理方法; */
    @ExceptionHandler
    public JsonResult doHandleRuntimeException(RuntimeException ex){
        String data = ex.getMessage();
        log.error("RuntimeException:" + data);
        return JsonResult.fail(data);
    }
}
