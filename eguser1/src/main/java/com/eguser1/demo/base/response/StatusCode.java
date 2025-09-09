package com.eguser1.demo.base.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusCode {
    OPERATION_SUCCESS(5000,"操作成功"),
    USER_NOT_EXISTS(7000,"用户不存在"),
    OPERATION_FAILED(6000,"操作失败");
    @Schema(description = "响应状态码",example = "2001")
    private Integer state;
    @Schema(description = "响应信息",example = "操作成功")
    private String message;
}
