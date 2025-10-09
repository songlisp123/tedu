package com.ivos.demo.base.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {

    @Schema(description = "响应状态码")
    private Integer code;
    @Schema(description = "响应信息")
    private String message;
    @Schema(description = "响应数据")
    private Object data;

    public JsonResult(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public JsonResult(StatusCode statusCode , Object data) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }


    public static JsonResult ok() {
        return new JsonResult(StatusCode.OPERATION_SUCCESS);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(StatusCode.OPERATION_SUCCESS,data);
    }

    public static JsonResult fail(Object data)  {
        return new JsonResult(StatusCode.OPERATION_FAILED,data);
    }
}
