package com.weiboLast.demo.base.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    @Schema(description = "响应状态",example = "2000")
    private Integer state;
    @Schema(description = "响应信息",example = "操作成功")
    private String message;
    @Schema(description = "响应数据",example = "null")
    private Object data;

    public JsonResult(StatusCode statusCode) {
        this.state = statusCode.getState();
        this.message = statusCode.getMessage();
    }

    public JsonResult(StatusCode statusCode , Object data) {
        this.state = statusCode.getState();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    public static JsonResult ok() {
        return new JsonResult(StatusCode.OPERATION_SUCCESS);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(StatusCode.OPERATION_SUCCESS,data);
    }
}
