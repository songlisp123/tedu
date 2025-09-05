package com.notice.demo.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusCode {
    //所有枚举类的实例必须放在最上面
    OPERATION_SUCCESS(2000,"操作成功"),
    OPERATION_FAILED(3000,"操作失败");

    private Integer status;
    private String message;
}
