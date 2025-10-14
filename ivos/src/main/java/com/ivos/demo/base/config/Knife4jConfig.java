package com.ivos.demo.base.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

public class Knife4jConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(getApiInfo())
                .tags(getTagsOrdered());
    }

    //定义API基本信息（包含作者信息）
    private Info getApiInfo() {
        return new Info()
                .title("IVOS API DOC")
                .description("智慧车辆运营管理系统接口文档")
                .version("1.0.0")
                .contact(new Contact().name("程晓宇"));
    }
    //定义Tag顺序
    private List<Tag> getTagsOrdered() {
        return Arrays.asList(
                new Tag().name("用户模块").description("用户管理相关接口"),
                new Tag().name("车辆模块").description("车辆管理相关接口"),
                new Tag().name("围栏模块").description("围栏管理相关接口"),
                new Tag().name("申请模块").description("用车申请管理相关接口"),
                new Tag().name("审批模块").description("用车审批管理相关接口"),
                new Tag().name("字典模块").description("字典管理相关接口"),
                new Tag().name("字典项模块").description("字典管理相关接口"),
                new Tag().name("申请单模块").description("字典管理相关接口"),
                new Tag().name("审核单模块").description("字典管理相关接口")
        );
    }
}
