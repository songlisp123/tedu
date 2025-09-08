package com.cloudCar.demo.pojo.DTO;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegParam {
    @Schema(description = "用户名",required = true,example = "示例（赵云）")
    private String username;
    @Schema(description = "密码",required = true,example = "示例（123456789）")
    private String password;
    @Schema(description = "邮箱",required = true,example = "xxxx.@xxx.com")
    private String email;
    @Schema(description = "电话号码R",required = true,example = "示例（198798712301）")
    private String phone;
}
