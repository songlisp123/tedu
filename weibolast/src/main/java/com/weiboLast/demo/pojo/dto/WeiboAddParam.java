package com.weiboLast.demo.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeiboAddParam {
    @Schema(description = "文章标题",required = true,example = "《大话西游》")
    private String title;
    @Schema(description = "文章内容 ",required = true,example = "haokan ")
    private String content;
}
