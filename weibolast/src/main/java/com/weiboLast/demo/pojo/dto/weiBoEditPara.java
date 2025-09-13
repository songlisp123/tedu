package com.weiboLast.demo.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class weiBoEditPara {
    @Schema(description = "微博id",required = true,example = "12")
    private Long id;
    @Schema(description = "用户 修改微博标题",required = true,example = "家人们谁懂啊？")
    private String title;
    @Schema(description = "修改微博正文内容",required = true,example = "你好")
    private String content;
}
