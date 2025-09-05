package com.egnotice0905.demo.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class comment {
    private Long id;
    private String content;
    private Data createTime;
    private Long userId;
    private Long weiboId;
}
