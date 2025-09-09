package com.eguser1.demo.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateParam {
    private Long id;
    private String username;
    private String password;
    private String nickname;;
}
