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
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class noticeController {

    @Autowired
    private noticeMapper noticeMapper;


    /**
     * 插入资讯
     * @param noticeDto 资讯视图对象
     * @return 字符串
     */
    @PostMapping("/v1/notice/add")
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
    public List<NoticeListVO> list(NoticeListQuery noticeListQuery) {
        List<NoticeListVO> list =
                noticeMapper.selectNoticeByTitleAndType(noticeListQuery);
        return list;
    }

    /**
     * 查询特定ID的咨询信息
     * @param id 咨询ID
     * @return {@code NoticeDetailInfoVO}视图
     */
    @GetMapping("/v1/notice/detail")
    public NoticeDetailInfoVO detail(Integer id) {
        return noticeMapper.selectNoticeById(id);
    }

    /**
     * 根据id删除咨询条目
     * @param id 将要删除的咨询条目
     * @return 条件字符串，表示删除成功或者失败
     */
    @PostMapping("/v1/notice/detele")
    public String delete(Integer id) {
        int i =noticeMapper.deleteNoticeById(id);
        if (i>0)
            return "删除成功！";
        return "该咨询不存在！删除失败";
    }

    /**
     * 从{@code noticeUpdateParam}中更新条目
     * @param noticeUpdateParam {@code notice} 的{@code DTO}视图对象，接受客户端传入的参数
     * @return {@code id==null} -- 返回id错误<br />
     * {@code notice == null} -- 返回暂无咨询<br />
     * 如果以上满足，则返回{@code 更新成功}或者{@code 更新失败}
     */
    @PostMapping("/v1/notice/update")
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

    /**
     * 根据ID查找{@code notice}对象
     * @param id 咨询条目id
     * @return 一个具体的咨询条目
     */
    @GetMapping("/v1/notice/sigal")
    public Notice sigal(Integer id) {
       return noticeMapper.selectById(id);
    }
}
