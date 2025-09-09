package com.weibo.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.weibo.demo.base.response.JsonResults;
import com.weibo.demo.base.response.StatusCode;
import com.weibo.demo.mapper.userMapper;
import com.weibo.demo.pojo.dto.UserDTO;
import com.weibo.demo.pojo.dto.UserLoginParam;
import com.weibo.demo.pojo.dto.UserRegParam;
import com.weibo.demo.pojo.entity.User;
import com.weibo.demo.pojo.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@Tag(name = "01用户模块")
@Validated
@RequestMapping("/v1/user/")
public class userController {

    @Autowired
    private userMapper userMapper;

    @PostMapping("reg")
    @Operation(summary = "用户注册",description = "用以注册新用户")
    @ApiOperationSupport(order = 100)
    public JsonResults reg(
            @RequestBody @Validated UserRegParam userRegParam) {
        log.debug("注册用户业务");
        //验证用户输入的名字和密码的id是否存在，如果存在，则注册失败
        //否则注册成功
        String username = userRegParam.getUsername();
        String password = userRegParam.getPassword();
        var users = userMapper.selectUserByUserNameAndPassword(
                username,password);
        if (users.length==0) {
            //你的插入逻辑
            User user = new User();
            BeanUtils.copyProperties(userRegParam,user);
            user.setCreateTime(new Date());
            int i = userMapper.insertUser(user);
            if (i>0)
                return JsonResults.ok();
            return new JsonResults(StatusCode.VALIDATED_ERROR);
        }
        log.debug("用户注册失败");
        return new JsonResults(StatusCode.USERNAME_EXISTS);
    }

    //用户登录模块
    @PostMapping("login")
    @Operation(summary = "用户登录",description = "测试用户登录")
    @ApiOperationSupport(order = 200)
    public JsonResults login(
            @RequestBody @Validated UserLoginParam userLoginParam,
            HttpSession session)
    {
        UserVo userVo = userMapper.selectUser(userLoginParam);
        if (userVo!=null) {
            session.setAttribute("user",userVo);
            return JsonResults.ok(userVo);
        }
        return new JsonResults(StatusCode.USERNAME_PASSWORD_ERROR);
    }

    //验证当前用户
    @GetMapping("currentUser")
    @Operation(summary = "验证用户 ",description = "验证用户登录状态")
    @ApiOperationSupport(order = 300)
    public JsonResults valid(HttpSession session) {
        UserVo userVo = (UserVo) session.getAttribute("user");
        return JsonResults.ok(userVo);
    }

    @GetMapping("logout")
    @Operation(summary = "退出登录",description = "此函数用以退出登录")
    @ApiOperationSupport(order = 301)
    public JsonResults logout(HttpSession session) {
        session.removeAttribute("user");
        return JsonResults.ok();
    }

    //修改个人信息
    @PostMapping("update")
    @Operation(summary = "修改个人密码",description = "此函数用于修改用户密码")
    @ApiOperationSupport(order = 250)
    @Parameters(value = {
            @Parameter(name = "username"),
            @Parameter(name = "nickname"),
            @Parameter(name = "userDTO",hidden = true)
    })
    public JsonResults update(
            @Validated UserDTO userDTO,
            HttpSession session)
    {
        UserVo userVo = (UserVo) session.getAttribute("user");
        if (userVo == null )
            return new JsonResults(StatusCode.NOT_LOGIN);
        Long userId = userVo.getId();
        int i = userMapper.updateUserInfo(userDTO,userId);
        if (i>0)
            return JsonResults.ok();
        return new JsonResults(StatusCode.OPERATION_FAILED);
    }

    //修改个人密码
    @PostMapping("changePassword")
    @Operation(summary = "修改个人密码")
    @ApiOperationSupport(order = 300)
    @Parameter(name = "password",required = true,description = "修改密码")
    public JsonResults changePwd(
            @Size(min = 8,max = 15,message = "密码格式错误") String password,
            HttpSession session
    )
    {
        UserVo userVo = (UserVo) session.getAttribute("user");
        if (userVo==null)
            return new JsonResults(StatusCode.NOT_LOGIN);
        Long userId = userVo.getId();
        int i = userMapper.updateUserPassWord(password,userId);
        if (i>0)
            return JsonResults.ok();
        return new JsonResults(StatusCode.OPERATION_FAILED);
    }
}
