package com.ivos.demo.dicOptions.pojo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictOptSavePara {
    private Long id;
    private String label;
    private String value;
    private Integer sort;
    private String remark;
    private Long dictId;
}
