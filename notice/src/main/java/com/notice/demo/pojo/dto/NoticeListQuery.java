package com.notice.demo.pojo.dto;

/**
 * {@code notice}视图对象，用来从客户端接受以下数据：<br />
 * {@code title} <br />
 * {@code type}
 */
public class NoticeListQuery {
    private String title;
    private Integer type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NoticeListQuery{" +
                "title='" + title + '\'' +
                ", type=" + type +
                '}';
    }
}
