package com._02vue.demo.user.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegPara {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String level;
    private String status;
}
