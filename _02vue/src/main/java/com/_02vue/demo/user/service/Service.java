package com._02vue.demo.user.service;

import com._02vue.demo.user.pojo.dto.UserLoginPara;
import com._02vue.demo.user.pojo.dto.UserQuery;
import com._02vue.demo.user.pojo.dto.UserRegPara;
import com._02vue.demo.user.pojo.dto.UserResetPassword;
import com._02vue.demo.user.pojo.entity.User;
import com._02vue.demo.user.pojo.vo.UserVo;

import java.util.List;


public interface Service {
    UserVo login(UserLoginPara loginPara);

    List<UserVo> selectUsers(UserQuery query);

    void save(UserRegPara para);

    void delete(Long userId);

    void updatePaasword(UserResetPassword password);
}
