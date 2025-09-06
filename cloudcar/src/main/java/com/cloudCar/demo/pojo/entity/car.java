package com.cloudCar.demo.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class car {
    private Long id;
    private String model;
    private String band;
    private String color;
    private String batteryType;
    private String code;
    private String displacement;
    private String license;
    private String type;
    private Date createTime;
    private Date updateTime;
    private Long userId;

}
