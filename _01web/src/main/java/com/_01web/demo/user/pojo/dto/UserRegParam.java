package com._01web.demo.user.pojo.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class UserRegParam {
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
}
