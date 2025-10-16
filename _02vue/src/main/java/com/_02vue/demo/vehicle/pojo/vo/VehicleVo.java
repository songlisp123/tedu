package com._02vue.demo.vehicle.pojo.vo;

import com._02vue.demo.vehicle.pojo.entity.Battery;
import com._02vue.demo.vehicle.pojo.entity.Color;
import com._02vue.demo.vehicle.pojo.entity.Status;
import com._02vue.demo.vehicle.pojo.entity.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
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

    public void setType(String type) {
        Type get = Arrays.stream(Type.values()).filter(
                        t -> t.getCode().equals(type))
                .findFirst().orElseGet(null);
        this.type = get.getMessage();
    }

    public void setColor(String color) {
        Color colored = Arrays.stream(Color.values()).filter(
                        t -> t.getCode().equals(color))
                .findFirst().orElseGet(null);
        this.color = colored.getMessage();
    }

    public void setBatteryType(String batteryType) {
        Battery battery = Arrays.stream(Battery.values()).filter(
                        t -> t.getCode().equals(batteryType))
                .findFirst().orElseGet(null);
        this.batteryType = battery.getMessage();
    }

    public void setStatus(String status) {
        Status status1 = Arrays.stream(Status.values()).filter(
                        t -> t.getCode().equals(status))
                .findFirst().orElseGet(null);
        this.status = status1.getMessage();
    }
}
