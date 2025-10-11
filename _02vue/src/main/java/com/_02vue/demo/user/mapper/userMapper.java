package com._02vue.demo.user.mapper;

import com._02vue.demo.user.pojo.dto.UserLoginPara;
import com._02vue.demo.user.pojo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface userMapper {
    User login(UserLoginPara loginPara);
}
