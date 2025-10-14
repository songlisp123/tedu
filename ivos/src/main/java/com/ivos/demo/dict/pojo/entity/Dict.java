package com.ivos.demo.dict.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dict {
    private Long id;
    private String code;
    private String name;
    private String remark;
    private String status;
    private Date createTime;
    private Date updateTime;
}
