package com.egnotice0905.demo.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResults {

    private Integer status;
    private String message;
    private Object data;

    public JsonResults(StatusCode statusCode) {
        this.status = statusCode.getStatus();
        this.message = statusCode.getMessage();
    }

    public JsonResults(StatusCode statusCode,Object data) {
        this.status = statusCode.getStatus();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    public static JsonResults ok() {
        return new JsonResults(StatusCode.OPERATION_SUCCESS);
    }

    public static JsonResults ok(Object data) {
        return new JsonResults(StatusCode.OPERATION_SUCCESS,data);
    }
}
