package com._06article.demo.base.Interceptor;

import com._06article.demo.pojo.VO.userVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception
    {
        HttpSession session = request.getSession();
        userVo user = (userVo) session.getAttribute("user");
        if (user==null) {
            response.sendRedirect("/login.html");
            return false;
        }
        return true;
    }

}
