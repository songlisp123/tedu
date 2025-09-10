package com.cloudCar.demo.base.Interceptors;

import com.cloudCar.demo.pojo.vo.userVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.logging.Logger;

@Slf4j
public class UserLoginCheckInterceptor implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger("car");
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception
    {
        logger.info("进入用户登录验证拦截器……");
        HttpSession session = request.getSession();
        String remoteHost = request.getRemoteHost();
        String remoteUser = request.getRemoteUser();
        int remotePort = request.getRemotePort();
        userVO user = (userVO) session.getAttribute("user");
        if (user==null) {
            logger.info("用户未登录");
            return false;
        }
        logger.info("用户验证成功，欢迎%s:%s 用户[%s]登录".formatted(remoteHost,remotePort,remoteUser));
        return true;
    }
}
