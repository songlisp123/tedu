package com.notice.demo.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;


/**
 * {@code notice}视图对象，用来从客户端接受以下数据：<br />
 * {@code title} <br />
 * {@code content} <br />
 * {@code type} <br />
 * {@code status}
 */
@Data
public class noticeDto {
    @Schema(description = "咨询标题",required = true,example = "喜羊羊与灰太狼")
    private String title;
    @Schema(description = "咨询内容",required = true)
    private String content;
    @Schema(description = "资讯类型",required = true,example = "实例：1")
    private Integer type;
    @Schema(description = "资讯状态",required = true,example = "实例：1")
    private Integer status;
}
