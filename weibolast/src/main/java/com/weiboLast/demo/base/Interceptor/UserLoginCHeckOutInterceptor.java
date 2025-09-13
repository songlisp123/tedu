package com.weiboLast.demo.base.Interceptor;

import com.weiboLast.demo.pojo.vo.UserVO;
import com.weiboLast.demo.pojo.vo.UserVO2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.logging.Logger;

@Slf4j
public class UserLoginCHeckOutInterceptor implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger("weibo");
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        logger.info("进入到用户登录拦截器……");
        HttpSession session = request.getSession();
        UserVO2 user = (UserVO2) session.getAttribute("user");
        if (user==null) {
            logger.warning("用户未登录！");
            throw new IllegalAccessError("权限访问失败");
        }
        logger.info("当前用户：%s".formatted(user.getUsername()));
        return true;
    }
}
