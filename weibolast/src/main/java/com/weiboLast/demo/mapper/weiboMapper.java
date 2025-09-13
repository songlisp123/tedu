package com.weiboLast.demo.mapper;

import com.weiboLast.demo.pojo.dto.weiBoEditPara;
import com.weiboLast.demo.pojo.entity.weiBo;
import com.weiboLast.demo.pojo.entity.weiBoTag;
import com.weiboLast.demo.pojo.vo.weiBoDetailVO;
import com.weiboLast.demo.pojo.vo.weiboIndexVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface weiboMapper {
    int insertWeiBo(weiBo weiBo);

    List<weiboIndexVo> selectWeiBo(
            @Param("pageNumber") Long pageNumber,
            @Param("maxMessageInOnePage") int maxMessageInOnePage);

    Long countAllWeiBo();

    weiBoDetailVO selectWeiBoById(Long id);

    /**
     * 此接口用于收集所有微博文章的id
     * @return id值得列表
     */
    List<Long> countAllWeiBoIds();

    int updateWeiBo(
            @Param("weiBoEditPara") weiBoEditPara weiBoEditPara,
            @Param("userId") Long userId);

    int delete(Long[] ids, Long userId);

    List<weiboIndexVo> searchWeiBo(String title);

    Integer selectTagId(String message);

    int insertWeiAndTag(weiBoTag e);
}
