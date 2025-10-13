package com._02vue.demo.user.pojo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Status {
    ACTIVE("✅","1"),
    INACTIVE("❌","0");
    private String code;
    private String message;
}
