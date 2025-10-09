package com._01web.demo.user.userController;

import com._01web.demo.base.response.JsonResults;
import com._01web.demo.user.pojo.dto.UserDtoV2;
import com._01web.demo.user.pojo.dto.UserLoginParam;
import com._01web.demo.user.pojo.dto.UserRegParam;
import com._01web.demo.user.pojo.vo.UserVo;
import com._01web.demo.user.service.userService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/v1/user/")
public class userController {

    @Autowired
    private userService userService;

    @PostMapping("login")
    public JsonResults login(@Validated @RequestBody UserLoginParam loginParam) {
        log.info("处理器处理请求：{}",loginParam);
        UserVo userVo =
                userService.findByUserNameAndPassword(loginParam);
        log.info("查询参数：{}",userVo);
        return JsonResults.ok(userVo);
    }

    @GetMapping("list")
    public JsonResults list() {
        log.info("这是一个展现用户列表的监事函数");
        List<UserVo> users = userService.findUsers();
        return JsonResults.ok(users);
    }

    @PostMapping("register")
    public JsonResults add(@RequestBody UserRegParam userRegParam) {
        //控制器调用业务层
        log.debug("注册参数：{}",userRegParam);
        //将参数交于业务层处理
        userService.addUser(userRegParam);
        return JsonResults.ok("用户注册成功！！");
    }

    @PostMapping("changePasswordConfirm")
    public JsonResults changePasswordConfirm(@RequestBody UserDtoV2 v2) {
        log.info("用户正在修改密码……");
        log.info("接受参数：{}",v2);
        //调用业务层处理提交的代码
        userService.updatePaassword(v2);
        return JsonResults.ok();
    }
}
