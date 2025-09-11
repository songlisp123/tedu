package com.weiboLast.demo.base.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusCode {
    NOT_LOGIN(1000,"未登录"),
    USERNAME_PASSWORD_ERROR(1001,"用户名或者密码错误"),
    USER_EXISTS(1002,"用户名被占用"),
    OPERATION_SUCCESS(2001,"操作成功"),
    OPERATION_ERROR(2002,"操作失败"),
    ARTICLES_NOT_EXISTS(3000,"文章不存在"),
    VALIDATION_ERROR(6000,"参数校检失败");
    private Integer state;
    private String message;
}
