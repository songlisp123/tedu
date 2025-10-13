package com._02vue.demo.user.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {
    private Long id;
    private String username;
    private String email;
    private String gender;
    private String level;
    private String status;
}
