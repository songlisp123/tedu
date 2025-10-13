package com.ivos.demo.base.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public enum StatusCode {
    OPERATION_SUCCESS(2000, "操作成功"),
    OPERATION_FAILED(3000, "操作失败"),
    VALIDATE_ERROR(3001, "参数校验失败"),
    DATA_UNEXISTS(3002, "请求数据不存在"),
    USERNAME_ALREADY_EXISTS(3003, "用户名被占用"),
    PASSWORD_ERROR(3004, "用户名或密码错误"),
    GEOFENCE_CAR_EXISTS(3007, "围栏存在未移除车辆"),
    USERNAME_ERROR(3005, "用户名或密码错误");

    //状态码、状态码描述
    private Integer code;
    private String message;
}
