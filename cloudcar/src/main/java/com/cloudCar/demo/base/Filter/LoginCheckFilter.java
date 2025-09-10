package com.cloudCar.demo.base.Filter;

import com.cloudCar.demo.pojo.vo.userVO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.logging.Logger;

@Slf4j
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = {"/v1/car/*"},description = "拦截车辆模块的全部请求")
public class LoginCheckFilter implements Filter {
    private static final Logger logger = Logger.getLogger("car");
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("过滤器初始化");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException
    {
        logger.info("进入登录状态的过滤器");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        userVO userVo = (userVO) session.getAttribute("user");
        if (userVo!=null) {
            logger.info("身份验证成功");
            filterChain.doFilter(request,response);
        } else {
            logger.warning("身份验证失败");
            response.sendRedirect("/login.html");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
