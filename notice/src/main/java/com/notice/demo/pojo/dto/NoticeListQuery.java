package com.notice.demo.pojo.dto;

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
