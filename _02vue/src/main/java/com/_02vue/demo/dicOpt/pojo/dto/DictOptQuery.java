package com._02vue.demo.dicOpt.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictOptQuery {

    private Long id;
    private Long dictId;
    private String label;
}
