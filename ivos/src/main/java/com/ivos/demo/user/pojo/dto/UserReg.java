package com.ivos.demo.user.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class UserReg {
    @Schema(description = "用户id值")
    private Long id;
    @Schema(description = "用户名称")
    private String username;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "年龄")
    private Integer age;
    @Schema(description = "性别")
    private String gender;
    @Schema(description = "状态")
    private String status;
    @Schema(description = "职级")
    private String level;
    @Schema(description = "上级领导")
    private Long parentId;
}
