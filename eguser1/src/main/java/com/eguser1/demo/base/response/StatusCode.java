package com.eguser1.demo.base.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusCode {
    OPERATION_SUCCESS(5000,"操作成功"),
    OPERATION_FAILED(6000,"操作失败");
    private Integer state;
    private String message;
}
