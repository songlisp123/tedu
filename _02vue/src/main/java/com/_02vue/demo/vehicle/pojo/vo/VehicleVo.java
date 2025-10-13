package com._02vue.demo.vehicle.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleVo {
    private Long id;
    private String brand;
    private String license;
    private String model;
    private String code;
    private String type; //车辆类型
    private String price;
    private String kilometers;
    private String displacement;
    private String color;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date regTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date buyTime;

    private String batteryType; //电池类型
    private String status; //车辆状态
}
