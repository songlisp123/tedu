package com.notice.demo.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 全局配置类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    private Integer status;
    private String message;
    private Object data;

    public JsonResult(StatusCode statusCode , Object data) {
        this.status = statusCode.getStatus();
        this.message = statusCode.getMessage();
        this.data = data;
    }
    public JsonResult(StatusCode statusCode) {
        this.status = statusCode.getStatus();
        this.message = statusCode.getMessage();
    }
}
