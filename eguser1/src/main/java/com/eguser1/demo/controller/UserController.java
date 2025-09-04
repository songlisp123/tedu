package com.eguser1.demo.controller;

import com.eguser1.demo.mapper.userMapper;
import com.eguser1.demo.pojo.DTO.UserAddParam;
import com.eguser1.demo.pojo.DTO.UserUpdateParam;
import com.eguser1.demo.pojo.DTO.loginDto;
import com.eguser1.demo.pojo.VO.UserListVO;
import com.eguser1.demo.pojo.eitity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/user/")
public class UserController {

    @Autowired
    private userMapper userMapper;

    @PostMapping("add/")
    public String add(UserAddParam userAddParam) {
        //先创建一个user对象
        User user = new User();
        //将userAddParam属性复制到user对象上面
        BeanUtils.copyProperties(userAddParam,user);
        //设置其他的user属性
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int i = userMapper.insertUser(user);
        if (i>0)
            return "插入成功！";
        return "插入失败";
    }

    @GetMapping("list/")
    public List<UserListVO> list() {
        return userMapper.selectAll();
    }

    @PostMapping("del/{id}/")
    public String del(@PathVariable Long id) {
        int i = userMapper.deleteUserById(id);
        if (i > 0)
            return "删除成功";
        return "查无此人";
    }

    @PostMapping("update/")
    public String update(UserUpdateParam userUpdateParam) {
        Long id = userUpdateParam.getId();
        if (id==null)
            return "暂无此人！";
        User user = userMapper.selectUserById(id);
        if (user == null)
            return "暂无此人";
        System.out.println(user);
        BeanUtils.copyProperties(userUpdateParam,user);
        user.setUpdateTime(new Date());
        int i = userMapper.updateUserInfo(user);
        System.out.println(user);
        if (i>0)
            return "更新成功";
        return "更新失败";
    }

    @GetMapping("get/")
    public User get(Long id) {
        return userMapper.selectUserById(id);
    }

    @PostMapping("login/")
    public String login(@RequestBody loginDto loginDto) {
        System.out.println(loginDto);
        return "登录成功";
    }

}
