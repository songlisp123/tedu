package com._02vue.demo.user.service;

import com._02vue.demo.user.pojo.dto.UserLoginPara;
import com._02vue.demo.user.pojo.entity.User;


public interface Service {
    User login(UserLoginPara loginPara);
}
