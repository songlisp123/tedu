package com._02vue.demo.dict.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dict {
    private Long id;
    private String name;
    private String code;
    private String status;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
