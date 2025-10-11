package com._02vue.demo.user.pojo.vo;

import lombok.Data;

@Data
public class UserVo {
    private Long id;
    private String username;
    private String password;
    private String email;
}
