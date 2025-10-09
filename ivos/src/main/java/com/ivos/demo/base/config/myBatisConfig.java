package com.ivos.demo.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ivos.demo.*.mapper")
public class myBatisConfig {
}
