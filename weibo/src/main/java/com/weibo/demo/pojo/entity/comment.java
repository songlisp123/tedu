package com.weibo.demo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class comment {
    private Long id;
    private String content;
    private Long userId;
    private Long weiBoId;
    private Date createTime;
}
