package com.ivos.demo.vehicle.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class VehicleVo {
    private Long id;
    private String brand;
    private String license;
    private String model;
    private String code;
    private String displacement;
    private String type;
    private String status;
    private String color;
    private String kilometers;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date regTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date buyTime;
    private String price;
    private String batteryType;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    private Date updateTime;
    private String geofenceBindStatus;
    private Long geofenceId;
}
