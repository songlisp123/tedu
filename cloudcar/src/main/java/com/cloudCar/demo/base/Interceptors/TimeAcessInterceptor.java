package com.cloudCar.demo.base.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class TimeAcessInterceptor implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger("car");
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception
    {
        logger.info("进入时间拦截器的业务范畴……");
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        if (hour>6&&hour<23) {
            logger.info("允许访问");
            return true;
        }
        logger.info("不允许访问！");
        throw new RuntimeException("未在规定的范围内访问！");
    }
}
