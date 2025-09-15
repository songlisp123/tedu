package com._09secutity.demo.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * EnableGlobalMethodSecurity注解: 激活鉴权功能的注解;
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //配置http的请求规则;
        http.
                authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/public/**").permitAll() //不认证
                        .anyRequest().authenticated() //认证
                )
                .formLogin(form -> form
                        .permitAll() //登录请求不认证
                )
                .logout(logout -> logout
                        .permitAll() //登出请求不认证
                );
        return http.build();
    }
}





