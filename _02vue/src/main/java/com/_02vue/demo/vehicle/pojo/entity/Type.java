package com._02vue.demo.vehicle.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Type {

    SEDAN("10","轿车"),
    TRUCK("20","货车"),
    BUS("30","客车");

    private String code;
    private String message;
}
