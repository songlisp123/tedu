package com._02vue.demo.user.controller;

import com._02vue.demo.base.response.JsonResult;
import com._02vue.demo.user.pojo.dto.UserLoginPara;
import com._02vue.demo.user.pojo.dto.UserQuery;
import com._02vue.demo.user.pojo.dto.UserRegPara;
import com._02vue.demo.user.pojo.dto.UserResetPassword;
import com._02vue.demo.user.pojo.entity.User;
import com._02vue.demo.user.pojo.vo.UserVo;
import com._02vue.demo.user.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        UserVo user = service.login(loginPara);
        return JsonResult.ok(user);
    }

    @GetMapping("query")
    public JsonResult query(UserQuery query) {
        List<UserVo> users = service.selectUsers(query);
        return JsonResult.ok(users);
    }

    @PostMapping("save")
    public JsonResult save(@RequestBody UserRegPara para) {
        log.debug("{}",para);
        service.save(para);
        return JsonResult.ok();
    }

    @PostMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long userId) {
        log.debug("控制器参数:{}",userId);
        service.delete(userId);
        return JsonResult.ok();
    }

    @PostMapping("updatePassword")
    public JsonResult updatePassword(@RequestBody UserResetPassword password)
    {
        log.debug("进入到修改密码的模块，参数：{}",password);
        service.updatePaasword(password);
        return JsonResult.ok();
    }
}
