package com.cloudCar.demo.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userVO {
    @Schema(description = "id",required = true)
    private Long id;
    @Schema(description = "用户名称",required = true)
    private String username;
}
