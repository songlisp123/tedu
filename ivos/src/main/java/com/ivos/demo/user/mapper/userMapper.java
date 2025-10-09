package com.ivos.demo.user.mapper;

import com.ivos.demo.user.pojo.vo.UserVo;
import org.springframework.stereotype.Repository;

@Repository
public interface userMapper {
    UserVo selectUserByUserNameAndPassword(String username);
}
