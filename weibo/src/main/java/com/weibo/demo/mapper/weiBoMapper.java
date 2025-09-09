package com.weibo.demo.mapper;

import com.weibo.demo.pojo.entity.weiBo;
import com.weibo.demo.pojo.vo.WeiBoDetailVO;
import com.weibo.demo.pojo.vo.weiBoIndex;

import java.util.List;

public interface weiBoMapper {
    int insertWeiBo(weiBo weibo);

    List<weiBoIndex> selectWeiBo();

    List<WeiBoDetailVO> selectWeiBoById(Long id);

    List<Long> selectAllWeiBoId();
}
