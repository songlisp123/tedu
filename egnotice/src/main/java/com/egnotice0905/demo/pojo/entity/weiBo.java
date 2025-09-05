package com.egnotice0905.demo.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class weiBo {
    private Long id;
    private String content;
    private Date createTime;
    private Long userId;
}
