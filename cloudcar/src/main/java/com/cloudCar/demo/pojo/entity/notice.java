package com.cloudCar.demo.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class notice {

    private Long id;
    private Long title;
    private String content;
    private Long userId;
    private Integer status;
    private Integer type;
    private Date createTime;
    private Date updateTime;

}
