package com._02vue.demo.user.mapper;

import com._02vue.demo.user.pojo.dto.UserLoginPara;
import com._02vue.demo.user.pojo.dto.UserQuery;
import com._02vue.demo.user.pojo.dto.UserRegPara;
import com._02vue.demo.user.pojo.dto.UserResetPassword;
import com._02vue.demo.user.pojo.entity.User;
import com._02vue.demo.user.pojo.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userMapper {
    UserVo login(UserLoginPara loginPara);

    List<UserVo> selectUsers(UserQuery query);

    void saved(User user);

    void update(User user);

    void delete(Long userId);

    void updatePassword(UserResetPassword password);
}
