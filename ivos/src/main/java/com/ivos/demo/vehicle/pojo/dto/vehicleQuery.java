package com.ivos.demo.vehicle.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class vehicleQuery {
    @Schema(description = "车辆id",example = "1")
    private Long id;
    @Schema( description = "车辆品牌",example = "小米su7")
    private String brand;
    @Schema(description = "车牌号",example = "豫A00x65")
    private String license;
}
