package com.weiboLast.demo.mapper;

import com.weiboLast.demo.pojo.entity.comment;

public interface commentMapper {
    int insertComment(comment comment);

    int deleteById(Long id, Long userId);
}
