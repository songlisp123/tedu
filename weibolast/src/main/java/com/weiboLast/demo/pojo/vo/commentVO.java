package com.weiboLast.demo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class commentVO {
    private Long id;
    private String content;
    private Date createTime;
    private String nickname;
//    private Long totalLikes;
//    private Long totalUnLikes;
}
