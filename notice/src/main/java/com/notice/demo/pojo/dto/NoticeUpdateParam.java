package com.notice.demo.pojo.dto;

import java.util.Date;

/**
 * {@code notice}视图对象，用来从客户端接受以下数据：<br />
 * {@code id }<br />
 * {@code title} <br />
 * {@code content} <br />
 * {@code type} <br />
 * {@code status}
 */
public class NoticeUpdateParam {
    private Long id;
    private String title;
    private String content;
    private Integer type;
    private Integer status;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "NoticeUpdateParam{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
