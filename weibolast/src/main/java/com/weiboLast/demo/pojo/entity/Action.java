package com.weiboLast.demo.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Action {
    LIKES(1,"用户喜欢这篇帖子"),
    UNLIKES(-1,"用户不喜欢次内容"),
    NOACTION(0,"用户不在乎这些内容");
    //用户对文章，评论的操作，在这个例子中1代表着喜欢
    //-1代表着不喜欢。0代表着不在乎,2代表着用户分享过该帖子
    private int action;
    //一个对当前操作的简单描述
    private String description;
}
