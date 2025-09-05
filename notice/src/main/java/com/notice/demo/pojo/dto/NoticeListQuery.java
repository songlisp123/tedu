package com.notice.demo.pojo.dto;

import lombok.Data;

/**
 * {@code notice}视图对象，用来从客户端接受以下数据：<br />
 * {@code title} <br />
 * {@code type}
 */
@Data
public class NoticeListQuery {
    private String title;
    private Integer type;
}
