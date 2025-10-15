package com.ivos.demo.user.userController;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivos.demo.base.response.JsonResult;
import com.ivos.demo.user.impl.UserServe;
import com.ivos.demo.user.pojo.dto.UserLoginParam;
import com.ivos.demo.user.pojo.dto.UserQuery;
import com.ivos.demo.user.pojo.dto.UserReg;
import com.ivos.demo.user.pojo.entity.User;
import com.ivos.demo.user.pojo.vo.UserVo;
import com.ivos.demo.user.pojo.vo.UserVoList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "用户模块")
@RequestMapping("/v1/user/")
public class UserController {

    @Autowired
    private UserServe userService;

    @PostMapping("login")
    @Operation(summary = "用户登录功能")
    @ApiOperationSupport(order = 100)
    public JsonResult add(
            @Validated
            @RequestBody
            UserLoginParam userLoginParam)
    {
        log.debug("当前参数,{}",userLoginParam);
        UserVo userVo = userService.login(userLoginParam);
        return JsonResult.ok(userVo);
    }

    @GetMapping("test")
    @Operation(summary = "检查是否能查询所有用户")
    @ApiOperationSupport(order = 200)
    public JsonResult find() {
        log.debug("进入到查询用户列表的功能块……");
        List<User> users = userService.findUsers();
        return JsonResult.ok(users);
    }

    @GetMapping("list")
    @Operation(summary = "查询所有用户")
    @ApiOperationSupport(order = 300)
    public JsonResult list() {
        log.debug("进入到……");
        List<UserVoList> list = userService.findAllUsers();
        return JsonResult.ok(list);
    }

    @GetMapping("lists")
    @Operation(summary = "用户列表")
    @ApiOperationSupport(order = 400)
    public JsonResult usersList(UserQuery userQuery) {
        log.debug("进入到用户查询功能模块……");
        List<UserVo> lists = userService.findUsers(userQuery);
        return JsonResult.ok(lists);
    }

    @PostMapping("reg")
    @Operation(summary = "用户注册")
    @ApiOperationSupport(order = 500)
    //能跑通
    public JsonResult reg(@RequestBody UserReg userReg) {
        log.debug("进入到注册用户的界面……");
        userService.regUser(userReg);
        return JsonResult.ok();
    }

    @PostMapping("update/password/{id}")
    @Operation(summary = "重置密码")
    @ApiOperationSupport(order = 600)
    public JsonResult updatePassword(@PathVariable("id") Long userId) {
        log.debug("进入到修改密码阶段");
        log.debug("当前用户id="+userId);
        userService.changePassword(userId);
        return JsonResult.ok();
    }

    @PostMapping("update/status/{id}/{status}")
    @Operation(summary = "修改用户状态")
    @ApiOperationSupport(order = 700)
    public JsonResult changeUserStatus(@PathVariable("id") Long userId,
                                       @PathVariable("status") String status)
    {
        log.debug("进入到修改状态的环节……");
        userService.changeStatus(userId,status);
        return JsonResult.ok();
    }


    @PostMapping("delete/{id}")
    @Operation(summary = "删除用户功能")
    @ApiOperationSupport(order = 800)
    public JsonResult delete(@PathVariable("id") Long userId)
    {
        log.debug("控制器方法:{}",userId);
        userService.delete(userId);
        return JsonResult.ok();
    }

    @GetMapping("audit/{parentId}")
    @Operation(summary = "查询上级领导")
    @ApiOperationSupport(order = 900)
    public JsonResult auditList(@PathVariable Long parentId) {
        log.debug("控制器接收参数:{}",parentId);
        List<UserVo> auditList = userService.selectByID(parentId);
        return JsonResult.ok(auditList);
    }
}
