package com.ivos.demo.user.impl;

import com.ivos.demo.base.exception.serviceException;
import com.ivos.demo.base.response.StatusCode;
import com.ivos.demo.base.util.passwordUtil;
import com.ivos.demo.user.mapper.userMapper;
import com.ivos.demo.user.pojo.dto.UserLoginParam;
import com.ivos.demo.user.pojo.dto.UserQuery;
import com.ivos.demo.user.pojo.dto.UserReg;
import com.ivos.demo.user.pojo.entity.User;
import com.ivos.demo.user.pojo.vo.UserVo;
import com.ivos.demo.user.pojo.vo.UserVoList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class UserServiceImpl implements UserServe  {

    @Autowired
    private userMapper userMapper;

    @Override
    public UserVo login(UserLoginParam userLoginParam) {
        log.debug("接收参数：{}",userLoginParam);
        String username = userLoginParam.getUsername();
        UserVo userVo = userMapper.selectUserByUserNameAndPassword(username);
        if ((Objects.isNull(userVo)) || (!Objects.equals(userLoginParam.getPassword(),userVo.getPassword()))) {
            log.warn("用户或者密码错误！");
            throw new serviceException(StatusCode.USERNAME_ERROR);
        }
        log.info("当前用户,{}",userVo.getUsername());
        return userVo;
    }

    @Override
    public List<User> findUsers() {
        List<User> users = userMapper.findUsers();
        return users;
    }

    @Override
    public List<UserVoList> findAllUsers() {
        List<UserVoList> users = userMapper.findAllUsers();
        return users;
    }

    @Override
    public List<UserVo> findUsers(UserQuery userQuery) {
        log.debug("进入到业务层面……");
        return userMapper.findAllUsers(userQuery);
    }

    @Override
    public void regUser(UserReg userReg) {
        log.debug("进入到业务层方面……");
        User newUser = new User();
//        newUser.setUsername(userReg.getUsername());
//        newUser.setPassword("123456");
//        newUser.setEmail(userReg.getEmail());
//        newUser.setPhone(userReg.getPhone());
//        newUser.setAge(userReg.getAge());
//        newUser.setGender(userReg.getGender());
//        newUser.setCreateTime(new Date());
//        newUser.setUpdateTime(new Date());
//        newUser.setStatus(userReg.getStatus());
//        newUser.setParentId(userReg.getParentId());
//        newUser.setLevel(userReg.getLevel());
        BeanUtils.copyProperties(userReg,newUser);
        if (newUser.getId() != null) {
            log.debug("更新用户");
            newUser.setUpdateTime(new Date());
            userMapper.updateUser(newUser);
        }

        else {
            log.debug("修改用户！");
            newUser.setPassword("123456");
            newUser.setCreateTime(new Date());
            newUser.setUpdateTime(new Date());
            userMapper.regUser(newUser);
        }

    }

    @Override
    public void changePassword(Long userId) {
        log.debug("进入到重置密码的业务层……");
        log.debug("当前参数："+userId);
        //这一步要获得新的用户
        User newUser = new User();
        newUser.setId(userId);
        newUser.setPassword(passwordUtil.generateRandomPassword(8));
        newUser.setUpdateTime(new Date());
        userMapper.updateUser(newUser);
    }

    @Override
    public void changeStatus(Long userId, String status) {
        log.debug("进入到业务层，{},{}",userId,status);
        User newUser = new User();
        newUser.setId(userId);
        newUser.setStatus(status);
        newUser.setUpdateTime(new Date());
        userMapper.updateUser(newUser);
    }

    @Override
    public void delete(Long userId) {
        log.debug("业务层参数：{}",userId);
        userMapper.delete(userId);
    }

    @Override
    public List<UserVo> selectByID(Long parentId) {
        log.debug("业务层参数:{}",parentId);
        //先定义一个列表存放查询数据
        ArrayList<UserVo> auditList = new ArrayList<>();
        //根据领导id查询领导
        UserVo audit01 = userMapper.selectById(parentId);
        //将一级领导添加到列表中
        auditList.add(audit01);
        //增加判断逻辑，判断一级领导是否有上级领导
        //如果有，继续遍历
        //如果没有，跳过此代码块
        if (audit01.getParentId() != null ) {
            UserVo audit02 = userMapper.selectById(audit01.getParentId());
            auditList.add(audit02);
        }
        return auditList;
    }
}
