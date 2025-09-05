package com.notice.demo.pojo.vo;

import lombok.Data;

import java.util.Date;


/**
 * 用来向客户端响应的数据：<br />
 * {@code id} <br />
 * {@code title} <br />
 * {@code content} <br />
 * {@code createTime} <br />
 * {@code type} <br />
 * {@code status} <br />
 * {@code userId}
 */
@Data
public class NoticeDetailInfoVO {
    private Long id;
    private String title;
    private String content;
    private Date createTime;
    private Integer type;
    private Integer status;
    private Long userId;

}
