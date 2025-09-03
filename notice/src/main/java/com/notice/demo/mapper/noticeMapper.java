package com.notice.demo.mapper;

import com.notice.demo.pojo.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface noticeMapper {
    int insertNotice(Notice notice);
}
