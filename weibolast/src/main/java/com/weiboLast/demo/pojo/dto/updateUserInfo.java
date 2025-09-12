package com.weiboLast.demo.pojo.dto;

import com.weiboLast.demo.pojo.entity.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class updateUserInfo {
    @Schema(description = "用户名称",required = true,example = "赵云")
    private String username;
    @Schema(description = "昵称",required = false,example = "小赵")
    private String nickname;
    @Schema(description = "性别",required = false,example = "男")
    private Gender gender;
}
