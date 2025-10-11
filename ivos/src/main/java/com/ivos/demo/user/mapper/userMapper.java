package com.ivos.demo.user.mapper;

import com.ivos.demo.user.pojo.dto.UserQuery;
import com.ivos.demo.user.pojo.dto.UserReg;
import com.ivos.demo.user.pojo.entity.User;
import com.ivos.demo.user.pojo.vo.UserVo;
import com.ivos.demo.user.pojo.vo.UserVoList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userMapper {
    UserVo selectUserByUserNameAndPassword(String username);

    List<User> findUsers();

    List<UserVoList> findAllUsers();

    List<UserVo> findAllUsers(UserQuery userQuery);

    int regUser(User user);

    int updateUser(User newUser);

    void delete(Long userId);
}
