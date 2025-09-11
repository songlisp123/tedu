package com.weiboLast.demo.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.weiboLast.demo.mapper")
public class MybatisConfig {

}
