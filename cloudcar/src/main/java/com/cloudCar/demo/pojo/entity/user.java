package com.cloudCar.demo.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class user {

    private Long id;
    private String username;
    private String password;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS",timezone = "Asia/shanghai")
    private Date createTime;
    private Date updateTime;
    private String phone;

}
