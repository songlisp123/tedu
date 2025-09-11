package com.weiboLast.demo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class weiBo {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private Date createTime;
    private Date updateTime;
}
