package com.eguser1.demo.mapper;

import com.eguser1.demo.pojo.VO.UserListVO;
import com.eguser1.demo.pojo.eitity.User;

import java.util.List;

public interface userMapper {
    int insertUser(User user);

    List<UserListVO> selectAll();

    int deleteUserById(Long id);

    User selectUserById(Long id);

    int updateUserInfo(User user);
}
