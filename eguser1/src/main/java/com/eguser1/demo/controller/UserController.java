package com.eguser1.demo.controller;

import com.eguser1.demo.base.response.JsonResults;
import com.eguser1.demo.base.response.StatusCode;
import com.eguser1.demo.mapper.userMapper;
import com.eguser1.demo.pojo.DTO.UserAddParam;
import com.eguser1.demo.pojo.DTO.UserUpdateParam;
import com.eguser1.demo.pojo.DTO.loginDto;
import com.eguser1.demo.pojo.eitity.User;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@Validated
@Tag(name = "01用户管理模块")
//reponsebody+controller
@RestController
@RequestMapping("/v1/user/")
public class UserController {

    @Autowired
    private userMapper userMapper;

    @Operation(summary = "添加用户")
    @ApiOperationSupport(order = 100)
    @PostMapping("add/")
    public JsonResults add(
            @RequestBody @Validated UserAddParam userAddParam)
    {
        log.debug("userAddParam:"+userAddParam);
        //先创建一个user对象
        User user = new User();
        //将userAddParam属性复制到user对象上面
        BeanUtils.copyProperties(userAddParam,user);
        //设置其他的user属性
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int i = userMapper.insertUser(user);
        if (i>0)
            return JsonResults.ok();
        return new JsonResults(StatusCode.OPERATION_FAILED);
    }

    @Operation(summary = "列出用户表")
    @ApiOperationSupport(order = 200)
    @GetMapping("list/")
    public JsonResults list() {
        var result= userMapper.selectAll();
        return JsonResults.ok(result);
    }

    @Operation(summary = "删除用户")
    @ApiOperationSupport(order = 300)
    @PostMapping("del/{id}/")
    public JsonResults del(
            @PathVariable @Range(min = 1L,max=100L,message = "id超出边界") Long id)
    {
        int i = userMapper.deleteUserById(id);
        if (i > 0)
            return JsonResults.ok();
        return new JsonResults(StatusCode.OPERATION_FAILED);
    }

    @PostMapping("update/")
    @Operation(summary = "更新用户")
    @ApiOperationSupport(order = 350)
    public JsonResults update(@RequestBody UserUpdateParam userUpdateParam) {
        Long id = userUpdateParam.getId();
        if (id==null)
            return new JsonResults(StatusCode.OPERATION_FAILED);
        User user = userMapper.selectUserById(id);
        if (user == null)
            return new JsonResults(StatusCode.OPERATION_FAILED);
        System.out.println(user);
        BeanUtils.copyProperties(userUpdateParam,user);
        user.setUpdateTime(new Date());
        int i = userMapper.updateUserInfo(user);
        System.out.println(user);
        if (i>0)
            return JsonResults.ok();
        return new JsonResults(StatusCode.OPERATION_FAILED);
    }

    @GetMapping("get/")
    @Operation(summary = "获取当前用户")
    @ApiOperationSupport(order = 400)
    public JsonResults get(Long id) {
        log.debug("删除用户id:"+id);
        User user =  userMapper.selectUserById(id);
        if (user==null)
            return new JsonResults(StatusCode.USER_NOT_EXISTS);
        return JsonResults.ok(user);
    }

    @PostMapping("login/")
    @Operation(summary = "登录用户")
    @ApiOperationSupport(order = 500)
    public JsonResults login(@RequestBody loginDto loginDto) {
        log.debug("loginDto:"+loginDto);
        System.out.println(loginDto);
        return JsonResults.ok();
    }

}
