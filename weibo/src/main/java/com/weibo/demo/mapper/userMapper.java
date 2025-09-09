package com.weibo.demo.mapper;

import com.weibo.demo.pojo.dto.UserDTO;
import com.weibo.demo.pojo.dto.UserLoginParam;
import com.weibo.demo.pojo.entity.User;
import com.weibo.demo.pojo.vo.UserVo;
import org.apache.ibatis.annotations.Param;

public interface userMapper {
    Long[] selectUserByUserNameAndPassword(String username, String password);

    int insertUser(User user);

    UserVo selectUser(UserLoginParam userLoginParam);

    int updateUserInfo(
            @Param("userDto") UserDTO userDTO,
            @Param("userId") Long userId);

    int updateUserPassWord(String password, Long userId);
}
