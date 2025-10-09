package com.ivos.demo.user.impl;

import com.ivos.demo.user.pojo.dto.UserLoginParam;
import com.ivos.demo.user.pojo.vo.UserVo;

public interface UserServe {

    UserVo login(UserLoginParam userLoginParam);
}
