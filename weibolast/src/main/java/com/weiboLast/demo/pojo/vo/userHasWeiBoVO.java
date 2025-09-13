package com.weiboLast.demo.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userHasWeiBoVO {
    private String username;
    private String nickname;
    private Long count;
}
