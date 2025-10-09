package com._01web.demo.base.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class JsonResults {

    private Integer code;
    private String message;
    private Object data;

    public JsonResults(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public JsonResults(StatusCode statusCode,Object data ) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    public static  JsonResults ok() {
        return new JsonResults(StatusCode.OPERATION_SUCCESS);
    }

    public static JsonResults ok(Object data) {
        return new JsonResults(StatusCode.OPERATION_SUCCESS,data);
    }

    public static JsonResults fail(Object data) {
        return new JsonResults(StatusCode.VALIDATE_ERROR,data);
    }
}
