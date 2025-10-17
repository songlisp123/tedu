package com._02vue.demo.dict.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DIctSavePara {
    private Long id;
    private String name;
    private String code;
    private String remark;
}
