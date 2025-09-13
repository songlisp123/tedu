package com.weiboLast.demo.mapper;

import com.weiboLast.demo.pojo.dto.UserLoginParam;
import com.weiboLast.demo.pojo.dto.UserRegParam;
import com.weiboLast.demo.pojo.dto.updateUserInfo;
import com.weiboLast.demo.pojo.dto.userChangePassword;
import com.weiboLast.demo.pojo.entity.CustomTag;
import com.weiboLast.demo.pojo.entity.User;
import com.weiboLast.demo.pojo.vo.UserVO;
import com.weiboLast.demo.pojo.vo.UserVO2;
import com.weiboLast.demo.pojo.vo.userHasWeiBoVO;
import com.weiboLast.demo.pojo.vo.weiboIndexVo;
import jakarta.validation.constraints.Positive;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface userMapper {
    Long selectUserByUserNameAndPassword(UserRegParam userRegParam);

    int insertUser(User user);

    UserVO2 selectUser(UserLoginParam userLoginParam);

    Long collectAllUserNumber();

    List<UserVO> showUserLists(
            @Positive @Param("pageNumber") Long pageNumber,
            @Param("number") int number);

    int updateInfo(
            @Param("userInfo") updateUserInfo updateUserInfo,
            @Param("userId") Long userId);

    int deleteUserById(Long id);

    int updateUserPassword(
            @Param("pwd") userChangePassword pwd,
            @Param("userID") Long userId);

    String[] selectPermession(Long id);

    List<weiboIndexVo> selectAllArticlesByUserId(Long userId);

    userHasWeiBoVO countAllArticles(Long userId);

    int insertUserAndTag(
            @Param("a") CustomTag e,
            @Param("userId") int i);
}
