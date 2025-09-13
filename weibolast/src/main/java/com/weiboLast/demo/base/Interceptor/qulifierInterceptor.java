package com.weiboLast.demo.base.Interceptor;

import com.weiboLast.demo.pojo.vo.UserVO2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Slf4j
public class qulifierInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger("weibo");

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        logger.info("进入到用户身份权限验证拦截");
        HttpSession session = request.getSession();
        UserVO2 user = (UserVO2) session.getAttribute("user");
        logger.info("user="+user.getUsername());
        Long id = user.getId();
        logger.info("尚未有权限！");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
