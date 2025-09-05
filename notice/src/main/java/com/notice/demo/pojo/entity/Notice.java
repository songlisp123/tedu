package com.notice.demo.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private Long id;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Integer type;
    private Integer status;
    private Long userId;
}
