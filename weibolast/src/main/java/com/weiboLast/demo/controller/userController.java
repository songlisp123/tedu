package com.weiboLast.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.weiboLast.demo.base.response.JsonResult;
import com.weiboLast.demo.base.response.StatusCode;
import com.weiboLast.demo.mapper.userMapper;
import com.weiboLast.demo.pojo.dto.UserLoginParam;
import com.weiboLast.demo.pojo.dto.UserRegParam;
import com.weiboLast.demo.pojo.entity.User;
import com.weiboLast.demo.pojo.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.logging.Logger;

@Slf4j
@RestController
@Tag(name = "01用户模块",description = "01用户模块")
@RequestMapping("/v1/user/")
public class userController {

    @Autowired
    private userMapper userMapper;
    private static final Logger logger = Logger.getLogger("weibo");

    @PostMapping("reg")
    @Operation(summary = "注册用户")
    @ApiOperationSupport(order = 100)
    public JsonResult reg(
            @RequestBody UserRegParam userRegParam) {
        //首先验证用户是否存在
        logger.info("进入用户注册界面……");
        Long id  =
                userMapper.selectUserByUserNameAndPassword(userRegParam);
        if (id!=null) {
            logger.info("用户已经存在，请重试！");
            return new JsonResult(StatusCode.USER_EXISTS);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegParam,user);
        user.setCreateTime(new Date());
        int i = userMapper.insertUser(user);
        if (i>0) {
            logger.info("注册用户成功！");
            logger.info("新用户:%s".formatted(user.getUsername()));
            return JsonResult.ok();
        }
        logger.warning("发生未知异常，导致注册失败，请重试！");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }

    @PostMapping("login")
    @Operation(summary = "用户登录")
    @ApiOperationSupport(order = 200)
    public JsonResult login(
            @RequestBody UserLoginParam userLoginParam,
            HttpSession session)
    {
        logger.info("进入用户登录界面……");
        UserVO userVo = userMapper.selectUser(userLoginParam);
        if (userVo==null) {
            logger.warning("用户名或者密码错误");
            return new JsonResult(StatusCode.USERNAME_PASSWORD_ERROR);
        }
        session.setAttribute("user",userVo);
        logger.info("登录成功");
        return JsonResult.ok(userVo);
    }

    @GetMapping("currentUser")
    @Operation(summary = "获取当前用户")
    @ApiOperationSupport(order = 300)
    public JsonResult current(
            HttpSession session
    ) {
        logger.info("进入到用户登录认证界面……");
        UserVO user = (UserVO) session.getAttribute("user");
        if (user==null) {
            logger.warning("暂未登录");
            return new JsonResult(StatusCode.NOT_LOGIN);
        }
        logger.info("用户已登录，用户：%s".formatted(user.getUsername()));
        return JsonResult.ok(user);
    }

    @GetMapping("logout")
    @Operation(summary = "退出登录")
    @ApiOperationSupport(order = 400)
    public JsonResult logout(HttpSession session) {
        logger.info("进入到用户退出登录功能块……");
        session.removeAttribute("user");
        logger.info("用户退出登录成功");
        return JsonResult.ok();
    }


}
