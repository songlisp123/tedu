package com.weibo.demo.mapper;

import com.weibo.demo.pojo.dto.UserLoginParam;
import com.weibo.demo.pojo.entity.User;
import com.weibo.demo.pojo.vo.UserVo;

public interface userMapper {
    Long[] selectUserByUserNameAndPassword(String username, String password);

    int insertUser(User user);

    UserVo selectUser(UserLoginParam userLoginParam);
}
