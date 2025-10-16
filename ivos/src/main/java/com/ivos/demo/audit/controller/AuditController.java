package com.ivos.demo.audit.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivos.demo.audit.pojo.dto.AudioQuery;
import com.ivos.demo.audit.pojo.dto.AuditSavePara;
import com.ivos.demo.audit.pojo.vo.AuditVo;
import com.ivos.demo.audit.service.AuditService;
import com.ivos.demo.base.response.JsonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "审核单模块")
@RestController
@RequestMapping("/v1/audit/")
public class AuditController {

    @Autowired
    private AuditService service;

    @Operation(summary = "查询申请单")
    @GetMapping("query")
    @ApiOperationSupport(order = 10)
    public JsonResult query(AudioQuery query) {
        log.debug("当前控制层参数:{}",query);
        List<AuditVo> list = service.query(query);
        return JsonResult.ok(list);
    }

    @PostMapping("update")
    @Operation(summary = "更新查询单状态")
    @ApiOperationSupport(order = 20)
    public JsonResult update(AuditSavePara para) {
        log.debug("控制器参数:{}",para);
        service.update(para);
        return JsonResult.ok();
    }
}
