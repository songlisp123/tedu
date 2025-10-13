package com._02vue.demo.vehicle.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Color {

    RED("10","红色"),
    BLUE("20","蓝色"),
    BLACK("30","黑色"),
    GREY("40","高级灰色"),
    WHITE("50","白色"),
    LIGHT_WHITE("60","银白色"),
    PINK("70","粉色"),
    GREEN("80","绿色"),
    DESIRE_COLOR("90","沙漠迷彩色"),
    TREES_COLOR("100","森林迷彩色"),
    OCEAN_COLOR("110","海洋迷彩色");
    private String code;
    private String message;

}
