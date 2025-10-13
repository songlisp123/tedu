package com._02vue.demo.vehicle.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegVehiclePara {

    private String brand;
    private String license;
    private String model;
    private String code;
    private String displacement;//可选
    private String type;//枚举
    private String color;//枚举
    private String kilometers;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date buyTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date regTime;
    private String price;
    private String batteryType;//枚举

}
