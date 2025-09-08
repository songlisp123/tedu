package com.cloudCar.demo.base.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonResult {
    @Schema(description = "状态",example = "2000")
    private Integer state;
    @Schema(description = "响应信息",example = "操作成功")
    private String message;
    @Schema(description = "响应数据",example = "null")
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
