package com.weibo.demo.mapper;

import com.weibo.demo.pojo.entity.comment;
import com.weibo.demo.pojo.vo.CommentVO;

import java.util.List;

public interface commentMapper {
    int insertComment(comment comment);

    List<CommentVO> selectAllcomments(Long id);
}
