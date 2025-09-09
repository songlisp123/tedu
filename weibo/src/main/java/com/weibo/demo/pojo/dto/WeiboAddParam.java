package com.weibo.demo.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeiboAddParam {
    @Schema(description = "微博内容",required = true,example = "你妈的")
    private String content;
}
