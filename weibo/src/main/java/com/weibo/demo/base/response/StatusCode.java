package com.weibo.demo.base.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusCode {
    NOT_LOGIN(1000,"未登录"),
    USERNAME_PASSWORD_ERROR(1001,"用户名或者密码错误"),
    USERNAME_EXISTS(1002,"用户已经存在"),
    OPERATION_SUCCESS(2001,"操作成功"),
    OPERATION_FAILED(2002,"操作失败"),
    VALIDATED_ERROR(6000,"认证失败");
    private Integer state;
    private String message;
}
