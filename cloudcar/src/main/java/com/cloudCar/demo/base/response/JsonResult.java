package com.cloudCar.demo.base.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonResult {
    private Integer state;
    private String message;
    private Object data;

    public JsonResult(StatussCode statussCode) {
        this.state = statussCode.getState();
        this.message = statussCode.getMessage();
    }

    public JsonResult(StatussCode statussCode,Object data) {
        this.state = statussCode.getState();
        this.message = statussCode.getMessage();
        this.data =data;
    }

    public static JsonResult ok() {
        return new JsonResult(StatussCode.OPERATION_SUCCESS);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(StatussCode.OPERATION_SUCCESS,data);
    }
}
