package com._01web.demo.user.service;

import com._01web.demo.base.response.StatusCode;
import com._01web.demo.user.mapper.userMapper;
import com._01web.demo.user.pojo.dto.UserDtoV2;
import com._01web.demo.user.pojo.dto.UserLoginParam;
import com._01web.demo.user.pojo.dto.UserRegParam;
import com._01web.demo.user.pojo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class userServiceImpl implements userService {

    @Autowired
    private userMapper userMapper;

    @Override
    public UserVo findByUserName(String username) {
        log.info("用户名称：{}",username);
        UserVo userVo =  userMapper.findByUserName(username);
        if (Objects.isNull(userVo)) {
            log.warn("尚未有此用户");
            throw new serviceException(StatusCode.USER_NOT_EXISTS);
        }
        return userVo;
    }

    @Override
    public List<UserVo> findUsers() {
        log.info("进入到用户模块业务层，处理用户请求……");
        List<UserVo> users = userMapper.findUsers();
        log.info("查询成功：{}",users);
        return users;
    }

    @Override
    public int addUser(UserRegParam userRegParam) {
        //先打印日志处理
        log.info("进入到用户业务层");
        log.debug("用户注册参数：{}",userRegParam);
        //调用数据层处理业务
        int i = userMapper.insertUser(userRegParam);
        if (i==0) {
            log.warn("操作未知异常！请重试");
            throw new serviceException(StatusCode.OPERATION_FAILED);
        }
        log.info("添加成功！用户：{}",userRegParam.getUsername());
        return i;
    }

    @Override
    public UserVo findByUserNameAndPassword(UserLoginParam loginParam) {
        //用户登录功能
        log.info("用户登录模块,{}",loginParam);
        //调用数据层查询
        UserVo userVo = userMapper.selectByUsernameAndPassword(loginParam);
        return  userVo;
    }

    @Override
    public int updatePaassword(UserDtoV2 v2) {
        log.info("进入到业务层面……");
        int i = userMapper.updatePassword(v2);
        if (i == 0) {
            log.info("发生未知错误！请重新是！");
            throw new serviceException(StatusCode.OPERATION_FAILED);
        }
        return i;
    }
}
