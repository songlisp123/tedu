package com.ivos.demo.vehicle.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class saveVehiclePara {
    @Schema(description = "汽车编号")
    private Long id;
    @Schema(description = "汽车品牌")
    private String brand;
    @Schema(description = "汽车车牌号")
    private String license;
    @Schema(description = "汽车模型")
    private String model;
    @Schema(description = "汽车标识码")
    private String code;
    @Schema(description = "排放量")
    private String displacement;
    @Schema(description = "类型")
    private String type;
    @Schema(description = "颜色")
    private String color;
    @Schema(description = "续航")
    private String kilometers;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "上牌时间")
    private Date regTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "购买时间")
    private Date buyTime;
    @Schema(description = "汽车价格")
    private String price;
    @Schema(description = "汽车电池类型")
    private String batteryType;
    @Schema(description = "围栏状态")
    private String geofenceBindStatus;
    @Schema(description = "围栏id")
    private Long geofenceId;

}
