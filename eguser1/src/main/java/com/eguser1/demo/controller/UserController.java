package com.eguser1.demo.controller;

import com.eguser1.demo.base.response.JsonResults;
import com.eguser1.demo.base.response.StatusCode;
import com.eguser1.demo.mapper.userMapper;
import com.eguser1.demo.pojo.DTO.UserAddParam;
import com.eguser1.demo.pojo.DTO.UserUpdateParam;
import com.eguser1.demo.pojo.DTO.loginDto;
import com.eguser1.demo.pojo.eitity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

//reponsebody+controller
@RestController

@RequestMapping("/v1/user/")
public class UserController {

    @Autowired
    private userMapper userMapper;

    @PostMapping("add/")
    public JsonResults add(UserAddParam userAddParam) {
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

    @GetMapping("list/")
    public JsonResults list() {
        var result= userMapper.selectAll();
        return JsonResults.ok(result);
    }

    @PostMapping("del/{id}/")
    public JsonResults del(@PathVariable Long id) {
        int i = userMapper.deleteUserById(id);
        if (i > 0)
            return JsonResults.ok();
        return new JsonResults(StatusCode.OPERATION_FAILED);
    }

    @PostMapping("update/")
    public JsonResults update(UserUpdateParam userUpdateParam) {
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
    public JsonResults get(Long id) {
        User user =  userMapper.selectUserById(id);
        return JsonResults.ok(user);
    }

    @PostMapping("login/")
    public JsonResults login(@RequestBody loginDto loginDto) {
        System.out.println(loginDto);
        return JsonResults.ok();
    }

}
