package com.weiboLast.demo.pojo.dto;

import com.weiboLast.demo.pojo.entity.CustomTag;
import com.weiboLast.demo.pojo.entity.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegParam {
    @Schema(description = "用户名称",required = true,example = "赵云")
    private String username;
    @Size(min=8,max = 15,message = "密码不符合规范！")
    @Schema(description = "密码",required = true,example = "xxxxxxxxxx")
    private String password;
    @Schema(description = "昵称",required = true,example = "子龙")
    private String nickname;
    @Schema(description = "用户性别",required = false,example = "男")
    private Gender gender;
    @Schema(description = "用户标签",required = true,example = "高兴")
    private List<CustomTag> tags;
}
