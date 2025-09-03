package com.notice.demo.controller;

import com.notice.demo.mapper.noticeMapper;
import com.notice.demo.pojo.dto.noticeDto;
import com.notice.demo.pojo.entity.Notice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class noticeController {

    @Autowired
    private noticeMapper noticeMapper;

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
}
