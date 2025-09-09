package com.weibo.demo.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegParam {
    @Size(min = 5,max = 15,message = "用户名必须是5-15之间")
    @Schema(description = "用户名称",required = true,example = "赵云")
    private String username;
    @Size(min = 6,max = 15,message = "用户密码必须处于5-15区间")
    @Schema(description = "用户密码",required = true,example = "A0116659")
    private String password;
    @Schema(description = "昵称",required = true,example = "小四")
    private String nickname;
}
