package com.weibo.demo.pojo.dto;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginParam {
    @Schema(description = "用户名称",required = true,example = "赵云")
    private String username;
    @Schema(description = "用户密码",required = true,example = "A0116659")
    private String password;
}
