package com._06article.demo.mapper;


import com._06article.demo.pojo.DTO.UserArticleParam;
import com._06article.demo.pojo.DTO.UserLoginParam;
import com._06article.demo.pojo.VO.articleVO;
import com._06article.demo.pojo.VO.userVo;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.Range;

public interface userMapper {
    userVo selectUser(UserLoginParam userLoginParam);

    int insertArticle(
            @Param("userArticleParam") UserArticleParam userArticleParam,
            @Param("userId") Long userId);

    articleVO selectArticleById(@Range(min = 1,max = 100,message = "超出文章范围") Long id);

    int deleteArticleById(@Range(min = 1,max = 100,message = "超出文章范围") Long id, Long userId);
}
