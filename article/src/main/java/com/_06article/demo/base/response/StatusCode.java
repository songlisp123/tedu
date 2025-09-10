package com._06article.demo.base.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StatusCode {
    OPERATION_SUCCESS(10110,"操作成功"),
    OPERATION_FAILED(10200,"操作失败"),
    ARTICLE_NOT_EXISTS(10600,"文章不存在"),
    USER_PASSWORD_ERROR(10300,"用户名或者密码错误"),
    NOT_LOGIN(10400,"用户未登录"),
    VALIDATED_ERROR(10500,"参数校检失败");
    private Integer state;
    private String message;
}
