package com._02vue.demo.user.controller;

import com._02vue.demo.base.response.JsonResult;
import com._02vue.demo.user.pojo.dto.UserLoginPara;
import com._02vue.demo.user.pojo.entity.User;
import com._02vue.demo.user.pojo.vo.UserVo;
import com._02vue.demo.user.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/user/")
public class userController {

    @Autowired
    private Service service;

    @PostMapping("login")
    public JsonResult login(@RequestBody UserLoginPara loginPara)
    {
        log.debug("控制器层面:{}",loginPara);
        User user = service.login(loginPara);
        return JsonResult.ok(user);
    }
}
