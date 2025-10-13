package com._02vue.demo.user.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Level {
    EMPLOYEE("10","职员"),
    MANAGER("20","经理");
    private String code;
    private String message;
}
