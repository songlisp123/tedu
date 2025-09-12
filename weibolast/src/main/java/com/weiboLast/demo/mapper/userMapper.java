package com.weiboLast.demo.mapper;

import com.weiboLast.demo.pojo.dto.UserLoginParam;
import com.weiboLast.demo.pojo.dto.UserRegParam;
import com.weiboLast.demo.pojo.dto.updateUserInfo;
import com.weiboLast.demo.pojo.entity.User;
import com.weiboLast.demo.pojo.vo.UserVO;
import jakarta.validation.constraints.Positive;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface userMapper {
    Long selectUserByUserNameAndPassword(UserRegParam userRegParam);

    int insertUser(User user);

    UserVO selectUser(UserLoginParam userLoginParam);

    Long collectAllUserNumber();

    List<UserVO> showUserLists(
            @Positive @Param("pageNumber") Long pageNumber,
            @Param("number") int number);

    int updateInfo(
            @Param("userInfo") updateUserInfo updateUserInfo,
            @Param("userId") Long userId);
}
