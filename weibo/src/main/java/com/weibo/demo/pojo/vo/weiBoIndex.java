package com.weibo.demo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class weiBoIndex {
    private Long id;
    private String content;
    private String nickname;
}
