package com.ivos.demo.application.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivos.demo.application.pojo.dto.ApplicationQuery;
import com.ivos.demo.application.pojo.dto.ApplicationSavePara;
import com.ivos.demo.application.pojo.vo.ApplicationVo;
import com.ivos.demo.application.service.ApplicationService;
import com.ivos.demo.base.response.JsonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "申请单模块")
@RestController
@RequestMapping("/v1/app/")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    @Operation(summary = "保存申请单")
    @PostMapping("save")
    @ApiOperationSupport(order=10)
    public JsonResult save(ApplicationSavePara para) {
        log.debug("控制器层接收参数:{}",para);
        service.save(para);
        return JsonResult.ok();
    }

    @GetMapping("query")
    @Operation(summary = "查询数据")
    @ApiOperationSupport(order = 20)
    public JsonResult query(ApplicationQuery query) {
        log.debug("控制器参数:{}",query);
        List<ApplicationVo> vos = service.query(query);
        return JsonResult.ok(vos);
    }

    @PostMapping("cancel/{id}")
    @Operation(summary = "撤销当前申请单")
    @ApiOperationSupport(order = 30)
    public  JsonResult cancel(@PathVariable("id") Long id) {
        log.debug("控制器参数:{}",id);
        service.cancel(id);
        return JsonResult.ok();
    }
}
