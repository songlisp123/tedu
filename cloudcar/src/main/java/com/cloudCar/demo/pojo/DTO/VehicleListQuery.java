package com.cloudCar.demo.pojo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleListQuery {
    @Schema(description = "汽车品牌",example = "小米")
    private String brand;
    @Schema(description = "许可",example = "许可")
    private String license;
}
