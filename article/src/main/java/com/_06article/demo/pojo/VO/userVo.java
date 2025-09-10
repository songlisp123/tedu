package com._06article.demo.pojo.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userVo {
    @Schema(description = "用户ID",example = "2")
    private Long id;
    @Schema(description = "用户名称",example = "赵云")
    private String username;
}
