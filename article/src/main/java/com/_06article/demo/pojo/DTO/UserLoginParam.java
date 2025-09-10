package com._06article.demo.pojo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginParam {
    @Schema(description = "用户名称",required = true,example = "赵云")
    private String username;
    @Schema(description = "密码",required = true,example = "A0116659")
    private String password;
}
