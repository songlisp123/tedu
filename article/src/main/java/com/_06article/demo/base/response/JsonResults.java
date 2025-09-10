package com._06article.demo.base.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResults {
    @Schema(description = "响应状态码",example = "2000")
    private Integer state;
    @Schema(description = "响应信息",example = "操作成功")
    private String message;
    @Schema(description = "响应数据")
    private Object data;

    public JsonResults(StatusCode statusCode) {
        this.state = statusCode.getState();
        this.message = statusCode.getMessage();
    }

    public JsonResults(StatusCode statusCode,Object data) {
        this.state = statusCode.getState();
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
