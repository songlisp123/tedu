package com._02vue.demo.vehicle.pojo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleQuery {

    private Long id;
    private String brand;
    private String status; //状态码
    private String license;
    private String geofenceBindStatus; //围栏状态吗
    private Long geofenceId;
}
