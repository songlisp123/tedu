package com.weiboLast.demo.mapper;

import com.weiboLast.demo.pojo.dto.UserLoginParam;
import com.weiboLast.demo.pojo.dto.UserRegParam;
import com.weiboLast.demo.pojo.entity.User;
import com.weiboLast.demo.pojo.vo.UserVO;

public interface userMapper {
    Long selectUserByUserNameAndPassword(UserRegParam userRegParam);

    int insertUser(User user);

    UserVO selectUser(UserLoginParam userLoginParam);
}
