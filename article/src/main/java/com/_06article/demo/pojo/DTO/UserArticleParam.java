package com._06article.demo.pojo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserArticleParam {
    @NotBlank(message = "文章标题不能为空")
    @Schema(description = "文章标题",required = true,example = "爱丽丝 漫游仙境")
    private String title;
    @Size(min = 50,max = 150,message = "文章不符合规范")
    @Schema(description = "文章内容",required = true,example = "爱丽丝穿过草地……")
    private String content;

}
