package com.weiboLast.demo.mapper;

import com.weiboLast.demo.pojo.entity.weiBo;
import com.weiboLast.demo.pojo.vo.weiboIndexVo;

import java.util.List;

public interface weiboMapper {
    int insertWeiBo(weiBo weiBo);

    List<weiboIndexVo> selectWeiBo(Long pageNumber);

    Long countAllWeiBo();

    weiboIndexVo selectWeiBoById(Long id);

    /**
     * 此接口用于收集所有微博文章的id
     * @return id值得列表
     */
    List<Long> countAllWeiBoIds();
}
