package com.notice.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.notice.demo.base.response.JsonResult;
import com.notice.demo.base.response.StatusCode;
import com.notice.demo.mapper.noticeMapper;
import com.notice.demo.pojo.dto.NoticeListQuery;
import com.notice.demo.pojo.dto.NoticeUpdateParam;
import com.notice.demo.pojo.dto.noticeDto;
import com.notice.demo.pojo.entity.Notice;
import com.notice.demo.pojo.vo.NoticeListVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "01-咨询模块")
@RequestMapping("/v1/notice/")
public class noticeController {

    @Autowired
    private noticeMapper noticeMapper;

    /**
     * 插入资讯
     * @param noticeDto 资讯视图对象
     * @return {@code JsonResult}对象
     */
    @PostMapping("add")
    @Operation(summary = "01添加咨询功能")
    @ApiOperationSupport(order = 10)
    @Parameters(value = {
            @Parameter(name = "title",description = "标题"),
            @Parameter(name = "content",description = "内容"),
            @Parameter(name = "type",description = "类型"),
            @Parameter(name = "status",description = "状态"),
            @Parameter(name = "noticeDto",hidden = true)
    })
    public JsonResult add(noticeDto noticeDto) {
        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeDto,notice);
        notice.setCreateTime(new Date());
        notice.setUpdateTime(new Date());
        notice.setUserId(1L);
        noticeMapper.insertNotice(notice);
        return JsonResult.ok();
    }

    /**
     * 根据用户传入的参数查询符合条件的咨询
     * @param noticeListQuery {@code notice}的DTO视图，用来接收<br />
     * 封装title和content的对象
     * @return {@code NoticeListVO}的集合视图
     */
    @GetMapping("list")
    @Operation(summary = "01咨询列表")
    @ApiOperationSupport(order = 40)
    public JsonResult list(NoticeListQuery noticeListQuery) {
        List<NoticeListVO> list =
                noticeMapper.selectNoticeByTitleAndType(noticeListQuery);
        return JsonResult.ok(list);
    }

    /**
     * 查询特定ID的咨询信息
     * @param id 咨询ID
     * @return {@code NoticeDetailInfoVO}视图
     */
    @GetMapping("detail")
    @Operation(summary = "01资讯详情")
    @ApiOperationSupport(order = 50)
    public JsonResult detail(Integer id) {
        var notice =  noticeMapper.selectNoticeById(id);
        return JsonResult.ok(notice);
    }

    /**
     * 根据id删除咨询条目
     * @param id 将要删除的咨询条目
     * @return 条件字符串，表示删除成功或者失败
     */
    @PostMapping("detele")
    @Operation(summary = "01删除咨询功能")
    @ApiOperationSupport(order = 20)
    @Parameter(name = "id",description = "资讯ID",required = true,example = "实例：1")
    public JsonResult delete(Integer id) {
        int i =noticeMapper.deleteNoticeById(id);
        if (i>0)
            return JsonResult.ok();
        return new JsonResult(StatusCode.OPERATION_FAILED);
    }

    /**
     * 从{@code noticeUpdateParam}中更新条目
     * @param noticeUpdateParam {@code notice} 的{@code DTO}视图对象，接受客户端传入的参数
     * @return {@code id==null} -- 返回id错误<br />
     * {@code notice == null} -- 返回暂无咨询<br />
     * 如果以上满足，则返回{@code 更新成功}或者{@code 更新失败}
     */
    @PostMapping("update")
    @ApiOperationSupport(order = 30)
    @Operation(summary = "01更新咨询功能")
    public JsonResult update(@RequestBody NoticeUpdateParam noticeUpdateParam) {
        Long id  = noticeUpdateParam.getId();
        if (id == null)
            return new JsonResult(StatusCode.OPERATION_FAILED);
        Notice notice = sigal(Math.toIntExact(id));
        if (notice == null)
            return new JsonResult(StatusCode.OPERATION_FAILED);
        BeanUtils.copyProperties(noticeUpdateParam,notice);
        notice.setUpdateTime(new Date());
        int i = noticeMapper.updateNotice(notice);
        if (i>0)
            return JsonResult.ok();
        return new JsonResult(StatusCode.OPERATION_FAILED);
    }

    /**
     * 根据{@code id}查找{@code notice}对象
     * @param id 咨询条目{@code id}
     * @return {@code notice}对象或者{@code null}
     */
    @GetMapping("sigal")
    public Notice sigal(Integer id) {
       return noticeMapper.selectById(id);
    }

    /**
     * 按照咨询的{@code type}参数查询
     * @param type 咨询的参数
     * @return Json对象
     */
    @GetMapping("{type}")
    public JsonResult selectByType(@PathVariable Integer type) {
        List<Notice> notices = noticeMapper.selectByType(type);
        return JsonResult.ok(notices);
    }

    /**
     * 按照咨询的状态进行查询
     * @param state 咨询的状态，整数类型
     * @return json对象
     */
    @GetMapping("status")
    public JsonResult status(Integer state) {
        List<Notice> notices = noticeMapper.selectByStatus(state);
        return JsonResult.ok(notices);
    }

    /**
     * 获取查询路径参数userId
     * @param userId 用户id参数
     * @return json对象
     */
    @GetMapping("userId")
    public JsonResult user(Long userId) {
        List<Notice> notices = noticeMapper.selectByUserId(userId);
        return JsonResult.ok(notices);
    }

    /**
     * 搜索所有的咨询信息条目
     * @return 前端接受的json信息格式的消息
     */
    @GetMapping("all")
    public JsonResult all() {
        List<Notice> list = noticeMapper.selectAll();
        return JsonResult.ok(list);
    }

    @GetMapping("content")
    public JsonResult content(String content) {
        List<Notice> list = noticeMapper.selectContent(content);
        return JsonResult.ok(list);
    }

    @GetMapping("title")
    public JsonResult title(String title) {
        List<Notice> list = noticeMapper.selectTitle(title);
        return JsonResult.ok(list);
    }

    /*
    根据最近更新排序
     */
    @GetMapping("recent")
    public JsonResult recent() {
        List<Notice> list = noticeMapper.sortByUpdateTime();
        return JsonResult.ok(list);
    }

    /*
    按照创建时间排序
     */
    @GetMapping("create")
    public JsonResult create() {
        List<Notice> list = noticeMapper.sortByCreateTime();
        return JsonResult.ok(list);
    }

    /*
    按照双重模糊查询
     */
    
}
