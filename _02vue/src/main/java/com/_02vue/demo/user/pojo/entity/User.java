package com._02vue.demo.user.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date createTime;
    private Date updateTime;
    private Gender gender;
    private Level level;
    private Status status;

    public String getGender() {
        return gender.getMessage();
    }

    public String getLevel() {
        return level.getMessage();
    }

    public String getStatus() {
        return status.getMessage();
    }

}
