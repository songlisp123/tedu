package com._06article.demo.pojo.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class articleVO {
    @Schema(description = "文章标题",example = "爱丽丝漫游仙境")
    private String title;
    @Schema(description = "文章内容",example = "爱丽丝跑过草地……")
    private String content;
    @Schema(description = "文章发布用户昵称",example = "小赵")
    private String nickname;
}
