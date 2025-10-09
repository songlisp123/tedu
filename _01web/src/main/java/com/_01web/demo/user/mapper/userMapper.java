package com._01web.demo.user.mapper;

import com._01web.demo.user.pojo.dto.UserDtoV2;
import com._01web.demo.user.pojo.dto.UserLoginParam;
import com._01web.demo.user.pojo.dto.UserRegParam;
import com._01web.demo.user.pojo.vo.UserVo;

import java.util.List;

public interface userMapper {
    UserVo findByUserName(String username);

    List<UserVo> findUsers();

    int insertUser(UserRegParam userRegParam);

    UserVo selectByUsernameAndPassword(UserLoginParam loginParam);

    int updatePassword(UserDtoV2 v2);
}
