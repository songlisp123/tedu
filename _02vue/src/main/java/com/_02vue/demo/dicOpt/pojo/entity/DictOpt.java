package com._02vue.demo.dicOpt.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictOpt {
    private Long id;
    private Long dictId;
    private String label;
    private String value;
    private Integer sort;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
