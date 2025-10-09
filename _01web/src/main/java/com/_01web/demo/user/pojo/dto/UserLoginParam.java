package com._01web.demo.user.pojo.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginParam {
    @NotBlank(message = "用户名不能为空！")
    private String auth;
    @NotBlank(message = "用户密码不能为空！")
    private String password;
}
