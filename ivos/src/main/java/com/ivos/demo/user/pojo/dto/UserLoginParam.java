package com.ivos.demo.user.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class UserLoginParam {

    @Schema(description = "用户名称")
    @NotBlank(message = "用户名不能为空！")
    private String username;
    @Schema(description = "用户密码")
    @NotBlank(message = "密码不能为空！")
    private String password;

}
