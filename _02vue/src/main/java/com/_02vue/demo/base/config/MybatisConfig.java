package com._02vue.demo.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com._02vue.demo.*.mapper")
public class MybatisConfig {
}
