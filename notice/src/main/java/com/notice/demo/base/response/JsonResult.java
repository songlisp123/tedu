package com.notice.demo.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    /**
     * 创建两个静态方法，制定用于操作成功的场景
     * 1、无返回值
     * 2、有返回值
     */

    public static JsonResult ok() {
        return new JsonResult(StatusCode.OPERATION_SUCCESS);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(StatusCode.OPERATION_SUCCESS,data);
    }
}
