package com.weiboLast.demo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Gender {
    MAN("♂","男"),
    WOMAN("♀","女");
    private String id;
    private String gender;
}
