package com._01web.demo.user.service;


import com._01web.demo.user.pojo.dto.UserDtoV2;
import com._01web.demo.user.pojo.dto.UserLoginParam;
import com._01web.demo.user.pojo.dto.UserRegParam;
import com._01web.demo.user.pojo.vo.UserVo;

import java.util.List;

public interface userService {

    UserVo findByUserName(String username);

    List<UserVo> findUsers();

    int addUser(UserRegParam userRegParam);

    UserVo findByUserNameAndPassword(UserLoginParam loginParam);

    int updatePaassword(UserDtoV2 v2);
}
