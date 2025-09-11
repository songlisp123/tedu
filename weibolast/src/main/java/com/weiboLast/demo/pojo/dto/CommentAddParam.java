package com.weiboLast.demo.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddParam {
    @Schema(description = "文章评论",required = true,example = "这篇文章又烂又臭又长……")
    private String content;
    @Schema(description = "微博id",required = true,example = "10")
    private Long weiboId;
}
