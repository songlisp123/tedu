package com.cloudCar.demo.pojo.DTO;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegParam {
//    @NotBlank(message = "用户名称不能为空")
    @Size(min = 5,max = 30,message = "用户名保持在5-30之间")
    @Schema(description = "用户名",required = true,example = "示例（赵云）")
    private String username;
    @NotBlank(message = "密码不能为空！")
    @Schema(description = "密码",required = true,example = "示例（123456789）")
    private String password;
    @Email(message = "邮箱格式错误")
    @Schema(description = "邮箱",required = true,example = "xxxx.@xxx.com")
    private String email;
    @Schema(description = "电话号码R",required = true,example = "示例（198798712301）")
    private String phone;
}
