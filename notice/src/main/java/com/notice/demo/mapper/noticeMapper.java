package com.notice.demo.mapper;

import com.notice.demo.pojo.dto.NoticeListQuery;
import com.notice.demo.pojo.entity.Notice;
import com.notice.demo.pojo.vo.NoticeDetailInfoVO;
import com.notice.demo.pojo.vo.NoticeListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface noticeMapper {
    int insertNotice(Notice notice);

    List<NoticeListVO> selectNoticeByTitleAndType(NoticeListQuery noticeListQuery);

    NoticeDetailInfoVO selectNoticeById(Integer id);

    int deleteNoticeById(Integer id);
}
