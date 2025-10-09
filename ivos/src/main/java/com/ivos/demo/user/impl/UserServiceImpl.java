package com.ivos.demo.user.impl;

import com.ivos.demo.base.exception.serviceException;
import com.ivos.demo.base.response.StatusCode;
import com.ivos.demo.user.mapper.userMapper;
import com.ivos.demo.user.pojo.dto.UserLoginParam;
import com.ivos.demo.user.pojo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
