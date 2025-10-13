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
    private Status status;
    private Type type;
    private Color color;
    private String kilometers;
    private Date regTime;
    private Date buyTime;
    private String price;
    private Battery batteryType;
    private Date createTime;
    private Date updateTime;
    private String geofenceBindStatus;
    private String geofenceId;


    public String getStatus() {
        return status.getCode();
    }

    public String getType() {
        return type.getCode();
    }

    public String getColor() {
        return color.getCode();
    }

    public String getBatteryType() {
        return batteryType.getCode();
    }
}
