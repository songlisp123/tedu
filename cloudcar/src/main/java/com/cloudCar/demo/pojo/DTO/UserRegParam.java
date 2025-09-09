package com.cloudCar.demo.pojo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegParam {
//    @NotBlank(message = "用户名称不能为空")
    @Size(min = 5,max = 30,message = "用户名保持在5-30之间")
    /*
    业务需求：
    用户名必须为5-15位
     */
    @Schema(description = "用户名",required = true,example = "示例（赵云）")
    private String username;
    @NotBlank(message = "密码不能为空！")
//    @Pattern(regexp = "")
    /*
    业务需求：长度为8-14个字符
    字母/数字以及标点符号至少包含两种以上
    不允许有空格和中文
     */
    @Schema(description = "密码",required = true,example = "示例（123456789）")
    private String password;
    @Email(message = "邮箱格式错误")
    /*
    要求：邮箱
     */
    @Schema(description = "邮箱",required = true,example = "xxxx.@xxx.com")
    private String email;
    /*
    要求：中国大陆手机号码
     */
    @Schema(description = "电话号码R",required = true,example = "示例（198798712301）")
    private String phone;
}
