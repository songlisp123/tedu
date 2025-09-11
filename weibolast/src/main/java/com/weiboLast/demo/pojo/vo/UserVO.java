package com.weiboLast.demo.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    @Schema(description = "用户唯一性id",example = "200")
    private Long id;
    @Schema(description = "用户名称",example = "赵云")
    private String username;
}
