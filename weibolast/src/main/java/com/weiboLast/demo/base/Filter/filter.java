package com.weiboLast.demo.base.Filter;

import com.weiboLast.demo.pojo.vo.UserVO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "filter",urlPatterns = "/v1/user/changePassword")
public class filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
        if (user!=null)
            filterChain.doFilter(request,response);
        else {
            response.sendRedirect("/login.html");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
