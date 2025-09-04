package com.notice.demo.pojo.vo;

import java.util.Date;

/**
 * 用来向客户端响应的数据：<br />
 * {@code id} <br />
 * {@code title} <br />
 * {@code createTime} <br />
 * {@code type} <br />
 * {@code status} <br />
 * {@code userId}
 */
public class NoticeListVO {
    private Long id;
    private String title;
    private Date createTime;
    private Integer type;
    private Integer status;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "NoticeListVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", type=" + type +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
