package com.weiboLast.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.weiboLast.demo.base.response.JsonResult;
import com.weiboLast.demo.base.response.StatusCode;
import com.weiboLast.demo.mapper.userMapper;
import com.weiboLast.demo.pojo.dto.UserLoginParam;
import com.weiboLast.demo.pojo.dto.UserRegParam;
import com.weiboLast.demo.pojo.dto.updateUserInfo;
import com.weiboLast.demo.pojo.entity.User;
import com.weiboLast.demo.pojo.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
@Tag(name = "01用户模块",description = "01用户模块")
@RequestMapping("/v1/user/")
public class userController {

    @Autowired
    private userMapper userMapper;
    private static final Logger logger = Logger.getLogger("weibo");
    private static final int MAX_USERS_IN_ONE_PAGE = 2;

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

    @GetMapping("userList")
    @Operation(summary = "用户列表")
    @ApiOperationSupport(order = 101)
    public JsonResult listAllUsers(
            @Positive
            @Schema(description = "查询路径参数",example = "10")
            Long pageNumber)
    {
        logger.info("用户列表");
        Long users = userMapper.collectAllUserNumber();
        Long l;
        if (users%MAX_USERS_IN_ONE_PAGE==0)
            l = users / MAX_USERS_IN_ONE_PAGE;
        else
            l=users / MAX_USERS_IN_ONE_PAGE + 1;
        if (pageNumber > l) {
            logger.info("超出范围，将重定向到最后一页");
            pageNumber=l;//如果超出范围，则定向到最后一页
        }
        logger.info("一共 %d 页,当前第 %d 页".formatted(l,pageNumber));
        pageNumber = MAX_USERS_IN_ONE_PAGE * (pageNumber-1);
        List<UserVO> list = userMapper.showUserLists(pageNumber, MAX_USERS_IN_ONE_PAGE);
        return JsonResult.ok(list);
    }

    //用户修改信息
//    @PostMapping("update")
//    @Operation(summary = "更改个人信息")
//    @ApiOperationSupport(order = 102)
//    public JsonResult update() {}
    //用户头像
    //用户性别ok
    //用户更新信息

    /*
    更新个人信息，需要验证用户是否登录
     */
    @PostMapping("update")
    @Operation(summary="更新个人资料")
    @ApiOperationSupport(order = 150)
    public JsonResult update(
            @RequestBody updateUserInfo updateUserInfo,
            HttpSession session
    )
    {
        logger.info("更新个人资料界面……");
        UserVO user = (UserVO) session.getAttribute("user");
        Long userId = user.getId();
        int i =
                userMapper.updateInfo(updateUserInfo,userId);
        if (i>0){
            logger.info("个人资料更新成功！");
            return JsonResult.ok();
        }
        logger.info("资料更新失败");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }
}
