package com.weibo.demo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class weiBo {
    private Long id;
    private String content;
    private Date createTime;
    private Long userID;
}
