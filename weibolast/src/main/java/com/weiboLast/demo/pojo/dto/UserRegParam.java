package com.weiboLast.demo.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegParam {
    @Schema(description = "用户名称",required = true,example = "赵云")
    private String username;
    @Schema(description = "密码",required = true,example = "xxxxxxxxxx")
    private String password;
    @Schema(description = "昵称",required = true,example = "子龙")
    private String nickname;
}
