package com.weiboLast.demo.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userChangePassword {
    @Schema(description = "用户密码",required = true,example = "xxxxxxxx")
    private String password;
    @Size(min=8,max = 15,message = "密码不符合规范")
    @Schema(description = "用户新密码",required = true,example = "xxxxxxxx")
    private String newPassword;
    @Schema(description = "确认用户新密码",required = true,example = "xxxxxxxx")
    private String confirmNewPassword;
}
