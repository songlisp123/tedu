package com._02vue.demo.user.service.imp;

import com._02vue.demo.user.mapper.userMapper;
import com._02vue.demo.user.pojo.dto.UserLoginPara;
import com._02vue.demo.user.pojo.entity.User;
import com._02vue.demo.user.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@org.springframework.stereotype.Service
public class ServiceImp implements Service {

    @Autowired
    private userMapper mapper;

    @Override
    public User login(UserLoginPara loginPara) {
        log.debug("业务层:{}",loginPara);
        User user = mapper.login(loginPara);
        //这里面有逻辑代码
        return user;
    }
}
