package com.cloudCar.demo.controller;

import com.cloudCar.demo.base.response.JsonResult;
import com.cloudCar.demo.base.response.StatussCode;
import com.cloudCar.demo.mapper.userMapper;
import com.cloudCar.demo.pojo.DTO.UserLoginParam;
import com.cloudCar.demo.pojo.DTO.UserRegParam;
import com.cloudCar.demo.pojo.entity.user;
import com.cloudCar.demo.pojo.vo.userVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@Tag(name = "01用户模块")
@RequestMapping("/v1/user/")
public class userController {

    @Autowired
    private userMapper userMapper;

    @PostMapping("add")
    @Operation(summary = "添加用户")
    @ApiOperationSupport(order = 100)
    public JsonResult add( @RequestBody @Validated UserRegParam userRegParam) {
        Long[] result =
                userMapper.Login(userRegParam);
        if (result.length != 0)
            return new JsonResult(StatussCode.USERNAME_EXISTS);
        user user = new user();
        BeanUtils.copyProperties(userRegParam,user);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insertUser(user);
        return JsonResult.ok();
    }

    @PostMapping("login")
    @Operation(summary = "登录模块")
    @ApiOperationSupport(order = 200)
    public JsonResult login(
            @RequestBody UserLoginParam userLoginParam,
            HttpSession httpSession)
    {
        userVO result =
                userMapper.Login(userLoginParam);
        if (result != null) {
            httpSession.setAttribute("user",result);
            return JsonResult.ok(result);
        }
        return new JsonResult(StatussCode.USERNAME_PASSWORD_ERROR);
    }

    //这些都是一些常见的功能，但是，对于更新信息来说，你必须登陆后才能更新咨询
    //同样只有登陆后才能尝试更多东西
    //不过，在你还没有登陆之前，有一些操作可以观看：
    /*
    *注册的用户
    *用户的个人信息列表
     */
    //首先完成用户列表
    @GetMapping("userList")
    @Operation(summary = "列表 功能")
    @ApiOperationSupport(order = 300)
    public JsonResult list() {
        List<user> users = userMapper.selectAllUsers();
        return JsonResult.ok(users);
    }

    //获取当前用户
    @GetMapping("currentUser")
    @Operation(summary="获取当前用户")
    @ApiOperationSupport(order = 201)
    public JsonResult currentUer(HttpSession session) {
        var userVo = session.getAttribute("user");
        return JsonResult.ok(userVo);
    }

    //退出登录成功
    @GetMapping("logout")
    @Operation(summary = "退出登录")
    @ApiOperationSupport(order = 110)
    public JsonResult logout(HttpSession session) {
        session.removeAttribute("user");
        return JsonResult.ok();
    }

    //搜索用户功能
    @GetMapping("search")
    @Operation(summary = "搜索用户功能")
    @ApiOperationSupport(order = 130)
    public JsonResult search(String username) {
        List<userVO> list = userMapper.searchUserByUserName(username);
        return JsonResult.ok(list);
    }

    //搜索特定ID的用户，采用路径参数的形式
    @GetMapping("search/{id}")
    @Operation(summary = "查询ID")
    @ApiOperationSupport(order = 131)
    public JsonResult searchId(@PathVariable Long id)
    {
        user user = userMapper.selectUserById(id);
        if (user==null)
            return new JsonResult(StatussCode.USERNAME_NPT_EXISTS);
        return JsonResult.ok(user);
    }

    //排序用户列表
    /*
    根据用户id排序，显示最先创建的用户
     */
    @GetMapping("sortById")
    @Operation(summary = "按照用户id排序")
    @ApiOperationSupport(order = 150)
    public JsonResult sortById() {
        List<userVO> list = userMapper.sortById();
        if (list==null)
            return new JsonResult(StatussCode.USERNAME_NPT_EXISTS);
        return JsonResult.ok(list);
    }

    /*
    按照创建时间排序
     */
    @GetMapping("sortByTime")
    @Operation(summary = "按照创建时间片排序")
    @ApiOperationSupport(order = 151)
    public JsonResult sortByTime() {
        List<userVO> list = userMapper.sortByTime();
        if (list==null)
            return new JsonResult(StatussCode.USERNAME_NPT_EXISTS);
        return JsonResult.ok(list);
    }

    /*
    按照最近的三个月显示
     */


}
