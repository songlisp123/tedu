package com.weiboLast.demo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum tag {
    TECH("科技","科技论文"),
    FOOD("美食","美食文章"),
    JOUR("旅游","旅游文章"),
    LIFE("生活","生活知识");
    private String message;
    private String description;
}
