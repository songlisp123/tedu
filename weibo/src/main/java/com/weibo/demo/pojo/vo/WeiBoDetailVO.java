package com.weibo.demo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeiBoDetailVO {
    private Long id;
    private String content;
    private Date createTime;
    private String nickname;
}
