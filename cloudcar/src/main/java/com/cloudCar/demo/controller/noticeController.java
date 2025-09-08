package com.cloudCar.demo.controller;

import com.cloudCar.demo.base.response.JsonResult;
import com.cloudCar.demo.mapper.noticeMapper;
import com.cloudCar.demo.pojo.vo.noticeVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "公告牌")
@RequestMapping("/v1/notice/")
public class noticeController {
    @Autowired
    private noticeMapper noticeMapper;

    @GetMapping("list")
    @Operation(summary = "查询首页公告")
    @ApiOperationSupport(order = 500)
    public JsonResult list() {
        List<noticeVO> list = noticeMapper.selectNotice();
        return JsonResult.ok(list);
    }
}
