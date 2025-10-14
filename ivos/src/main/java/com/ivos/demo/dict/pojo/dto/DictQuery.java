package com.ivos.demo.dict.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictQuery {

    @Schema(description = "字典id")
    private Long id;
    @Schema(description = "字典名称")
    private String name;
    @Schema(description = "字典编码")
    private String code;
}
