package com.ivos.demo.dicOptions.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivos.demo.base.response.JsonResult;
import com.ivos.demo.dicOptions.pojo.dto.DicOptQuery;
import com.ivos.demo.dicOptions.pojo.dto.DictOptSavePara;
import com.ivos.demo.dicOptions.pojo.vo.DicOptVo;
import com.ivos.demo.dicOptions.service.DicOptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "字典项模块")
@RestController
@RequestMapping("/v1/dicOpt/")
public class dictOptController {

    @Autowired
    private DicOptService service;

    @GetMapping("query")
    @Operation(summary = "查找当前字典项")
    @ApiOperationSupport(order = 10)
    public JsonResult query(DicOptQuery query) {
        log.debug("控制器查询参数,{}",query);
        List<DicOptVo> dicOptVos = service.query(query);
        return JsonResult.ok(dicOptVos);
    }

    @PostMapping("save")
    @Operation(summary = "保存字典项")
    @ApiOperationSupport(order = 20)
    public JsonResult save(DictOptSavePara para) {
        log.debug("控制器参数，{}",para);
        service.save(para);
        return JsonResult.ok();
    }

    @PostMapping("delete/{id}")
    @Operation(summary = "删除字典项")
    @ApiOperationSupport(order = 30)
    public JsonResult delete(@PathVariable("id") Long dicOptId) {
        log.debug("控制器参数:{}",dicOptId);
        service.delete(dicOptId);
        return JsonResult.ok();
    }
}
