package com.cloudCar.demo.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class user {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date createTime;
    private Date updateTime;
    private String phone;

}
