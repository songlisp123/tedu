package com.cloudCar.demo.pojo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginParam {
    @Schema(description = "用户名称",required = true,example = "示例（赵云）")
    private String username;
    @Schema(description = "密码",required = true,example = "示例(123456789)")
    private String password;
}
