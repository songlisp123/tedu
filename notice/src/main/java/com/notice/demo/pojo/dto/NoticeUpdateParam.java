package com.notice.demo.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * {@code notice}视图对象，用来从客户端接受以下数据：<br />
 * {@code id }<br />
 * {@code title} <br />
 * {@code content} <br />
 * {@code type} <br />
 * {@code status}
 */
@Data
public class NoticeUpdateParam {
    private Long id;
    private String title;
    private String content;
    private Integer type;
    private Integer status;
}
