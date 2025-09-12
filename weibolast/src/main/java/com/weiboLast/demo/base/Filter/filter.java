package com.weiboLast.demo.base.Filter;

import com.weiboLast.demo.pojo.vo.UserVO;
import com.weiboLast.demo.pojo.vo.UserVO2;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

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
        UserVO2 user = (UserVO2) session.getAttribute("user");
        if (user==null) {
            response.sendRedirect("/login.html");
            return;
        }
        String password = user.getPassword();
        String password1 = request.getParameter("password");
        if (!Objects.equals(password,password1)) {
            response.sendRedirect("/login.html");
            return;
        }
        String newPassword = request.getParameter("newPassword");
        String newPassword1 = request.getParameter("confirmNewPassword");
        if (!Objects.equals(newPassword,newPassword1)){
            response.sendRedirect("/login.html");
            return;
        }
        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
