package com.notice.demo.controller;

import com.notice.demo.mapper.noticeMapper;
import com.notice.demo.pojo.dto.NoticeListQuery;
import com.notice.demo.pojo.dto.NoticeUpdateParam;
import com.notice.demo.pojo.dto.noticeDto;
import com.notice.demo.pojo.entity.Notice;
import com.notice.demo.pojo.vo.NoticeDetailInfoVO;
import com.notice.demo.pojo.vo.NoticeListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class noticeController {

    @Autowired
    private noticeMapper noticeMapper;


    /**
     * 插入资讯
     * @param noticeDto 资讯视图对象
     * @return 字符串
     */
    @PostMapping("/v1/notice/add")
    @ResponseBody
    public String add(noticeDto noticeDto) {
        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeDto,notice);
        notice.setCreateTime(new Date());
        notice.setUpdateTime(new Date());
        notice.setUserId(1L);
        noticeMapper.insertNotice(notice);
        return "添加成功！";
    }

    /**
     * 根据用户传入的参数查询符合条件的咨询
     * @param noticeListQuery {@code notice}的DTO视图，用来接收<br />
     * 封装title和content的对象
     * @return {@code NoticeListVO}的集合视图
     */
    @GetMapping("/v1/notice/list")
    @ResponseBody
    public List<NoticeListVO> list(NoticeListQuery noticeListQuery) {
        List<NoticeListVO> list =
                noticeMapper.selectNoticeByTitleAndType(noticeListQuery);
        return list;
    }

    @GetMapping("/v1/notice/detail")
    @ResponseBody
    public NoticeDetailInfoVO detail(Integer id) {
        return noticeMapper.selectNoticeById(id);
    }

    @PostMapping("/v1/notice/detele")
    @ResponseBody
    public String delete(Integer id) {
        int i =noticeMapper.deleteNoticeById(id);
        if (i>0)
            return "删除成功！";
        return "该咨询不存在！删除失败";
    }

    @PostMapping("/v1/notice/update")
    @ResponseBody
    public String update(NoticeUpdateParam noticeUpdateParam) {
        Long id  = noticeUpdateParam.getId();
        if (id == null)
            return "ID错误！请检查传入参数！";
        Notice notice = sigal(Math.toIntExact(id));
        if (notice == null)
            return "暂无咨询！";
        BeanUtils.copyProperties(noticeUpdateParam,notice);
        notice.setUpdateTime(new Date());
        int i = noticeMapper.updateNotice(notice);
        if (i>0)
            return "更新成功!";
        return "更新失败！";
    }

    @GetMapping("/v1/notice/sigal")
    @ResponseBody
    public Notice sigal(Integer id) {
       return noticeMapper.selectById(id);
    }
}
