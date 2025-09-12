package com.weiboLast.demo.pojo.vo;

import com.weiboLast.demo.pojo.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO2 {
    private Long id;
    private String username;
    private String password;
    private String nickname;
}
