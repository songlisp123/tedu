package com._02vue.demo.vehicle.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    private Long id;
    private String brand;
    private String license;
    private String model;
    private String code;
    private String displacement;
    private String status;
    private String type;
    private String color;
    private String kilometers;
    private Date regTime;
    private Date buyTime;
    private String price;
    private String batteryType;
    private Date createTime;
    private Date updateTime;
    private String geofenceBindStatus;
    private Long geofenceId;


}
