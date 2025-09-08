package com.cloudCar.demo.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAddParam {

    private String model;
    private String brand;
    private String color;
    private String batteryType;
    private String code;
    private String displacement;
    private String license;
    private String type;

}
