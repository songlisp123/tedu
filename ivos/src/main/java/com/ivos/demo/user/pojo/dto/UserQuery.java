package com.ivos.demo.user.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class UserQuery {
    //明明只有两个参数？为什么需要id和职级呢？
    @Schema(description = "用户id",example ="12" )
    private Long id;
    @Schema(description ="用户名称",example ="宋宁龙")
    private String username;
    @Schema(description ="用户当前状态",example ="离职")
    private String status;
    @Schema(description ="职级",example ="xxx")
    private String level;
}
