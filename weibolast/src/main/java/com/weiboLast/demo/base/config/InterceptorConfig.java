package com.weiboLast.demo.base.config;

import com.weiboLast.demo.base.Interceptor.UserLoginCHeckOutInterceptor;
import com.weiboLast.demo.base.Interceptor.qulifierInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginCHeckOutInterceptor())
                .addPathPatterns("/v1/user/logout",
                        "/v1/weibo/insert",
                        "/v1/comment/insert",
                        "/v1/comment/del/{id}",
                        "/v1/user/update",
                        "/v1/user/del/{id}",
                        "/v1/user/changePassword",
                        "/v1/user/test",
                        "/v1/weibo/edit",
                        "/v1/weibo/del");

        registry.addInterceptor(new qulifierInterceptor())
                .addPathPatterns("/v1/user/del/{id}");
    }
}
