package com.weiboLast.demo.mapper;

import com.weiboLast.demo.pojo.entity.comment;
import com.weiboLast.demo.pojo.vo.commentVO;

import java.util.List;

public interface commentMapper {
    int insertComment(comment comment);

    int deleteById(Long id, Long userId);

    List<commentVO> listAllCommentsByWeiBoId(Long id);
}
