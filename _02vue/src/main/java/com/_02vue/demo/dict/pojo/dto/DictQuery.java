package com._02vue.demo.dict.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictQuery {

    private Long id;
    private Integer currentPage;
    private Integer pageSize;
    private String name;
    private String code;
}
