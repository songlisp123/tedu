package com.weiboLast.demo.base.qulifierCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum qulifyCode {
    LIST_ALL_USERS("B00","查看用户列表"),
    UPDATE_ARTICLE("B01","修改文章"),
    CHANGE_PASSWORD("B02","修改密码"),
    DELETE_ARTICLE("B20","删除文章"),
    CHANGE_COMMENTS("B25","修改评论"),
    DELETE_COMMENTS("B30","删除评论"),
    DELETE_USER("B10","删除用户");
    private String stateCode;
    private String detail;
}
