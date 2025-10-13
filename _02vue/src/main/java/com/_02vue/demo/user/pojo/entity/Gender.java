package com._02vue.demo.user.pojo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public enum Gender {
    MALE("♂","男"),
    FEMALE("♀","女");
    private String code;
    private String message;

}
