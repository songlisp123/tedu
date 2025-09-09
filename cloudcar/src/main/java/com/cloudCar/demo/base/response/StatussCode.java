package com.cloudCar.demo.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatussCode {
    OPERATION_SUCCESS(2000,"操作成功"),
    OPERATION_FAILED(3000,"操作失败"),
    USERNAME_EXISTS(1000,"用户已经存在"),
    USERNAME_NPT_EXISTS(1002,"用户不存在"),
    CAR_NOT_EXISTS(1005,"车辆不存在"),
    USERNAME_PASSWORD_ERROR(1001,"用户名或者密码失败"),
    VALIDATED_ERROR(9998,"参数校验异常"),
    NOT_LOGIN(1002,"未登录");
    private Integer state;
    private String message;
}
