package com.weiboLast.demo.pojo.entity;

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
    private Date createTime;
    private Long userId;
    private Long weiboId;
}
