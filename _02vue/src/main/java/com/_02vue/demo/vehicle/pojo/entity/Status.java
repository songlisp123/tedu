package com._02vue.demo.vehicle.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Status {

    WORKING("0","占用"),
    LEISURE("1","空闲"),
    FIXING("2","维修"),
    RENTING("3","租赁");

    private String code;
    private String message;
}
