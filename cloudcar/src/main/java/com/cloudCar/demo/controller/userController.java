package com.cloudCar.demo.controller;

import com.cloudCar.demo.base.response.JsonResult;
import com.cloudCar.demo.base.response.StatussCode;
import com.cloudCar.demo.mapper.userMapper;
import com.cloudCar.demo.pojo.DTO.UserLoginParam;
import com.cloudCar.demo.pojo.DTO.UserRegParam;
import com.cloudCar.demo.pojo.entity.user;
import com.cloudCar.demo.pojo.vo.userVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/user/")
public class userController {

    @Autowired
    private userMapper userMapper;

    @PostMapping("add")
    public JsonResult add( @RequestBody UserRegParam userRegParam) {
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
    public JsonResult login(@RequestBody UserLoginParam userLoginParam) {
        userVO result =
                userMapper.Login(userLoginParam);
        if (result != null)
            return JsonResult.ok(result);
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
    public JsonResult list() {
        List<user> users = userMapper.selectAllUsers();
        return JsonResult.ok(users);
    }

}
