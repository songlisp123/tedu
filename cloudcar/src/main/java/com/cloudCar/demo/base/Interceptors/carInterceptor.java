package com.cloudCar.demo.base.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Slf4j
public class carInterceptor implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger("car");
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception
    {

        System.out.println("拦截预处理");
        logger.info("拦截预处理");
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception
    {
        System.out.println("拦截处理要求");
        logger.info("拦截额后处理");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, Exception ex) throws Exception
    {
        System.out.println("拦截处理完毕");
        logger.info("拦截处理完毕");
    }
}
