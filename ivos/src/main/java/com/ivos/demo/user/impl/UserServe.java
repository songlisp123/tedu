package com.ivos.demo.user.impl;

import com.ivos.demo.user.pojo.dto.UserLoginParam;
import com.ivos.demo.user.pojo.dto.UserQuery;
import com.ivos.demo.user.pojo.dto.UserReg;
import com.ivos.demo.user.pojo.entity.User;
import com.ivos.demo.user.pojo.vo.UserVo;
import com.ivos.demo.user.pojo.vo.UserVoList;

import java.util.List;

public interface UserServe {

    UserVo login(UserLoginParam userLoginParam);

    List<User> findUsers();

    List<UserVoList> findAllUsers();

    List<UserVo> findUsers(UserQuery userQuery);

    void regUser(UserReg userReg);

    void changePassword(Long userId);

    void changeStatus(Long userId, String status);

    void delete(Long userId);
}
