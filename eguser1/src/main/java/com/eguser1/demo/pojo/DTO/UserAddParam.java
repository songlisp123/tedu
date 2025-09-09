package com.eguser1.demo.pojo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddParam {
    @Size(min = 5,max = 12,message = "用户名错误")
    private String username;
    @NotBlank(message = "用户名不能为空或者空字符串")
    private String password;
    @Nullable
    private String nickname;
}
