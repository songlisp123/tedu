package com.ivos.demo.dict.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivos.demo.base.response.JsonResult;
import com.ivos.demo.dict.pojo.dto.DictQuery;
import com.ivos.demo.dict.pojo.dto.DictRegPara;
import com.ivos.demo.dict.pojo.vo.DictVo;
import com.ivos.demo.dict.service.DictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "字典模块")
@RestController
@RequestMapping("/v1/dict/")
public class DictController {

    @Autowired
    private DictService service;

    @GetMapping("query")
    @Operation(summary = "查询字典")
    @ApiOperationSupport(order = 10)
    public JsonResult query(DictQuery query) {
        log.debug("控制器接收参数:{}",query);
        List<DictVo> list = service.query(query);
        return JsonResult.ok(list);
    }

    @PostMapping("save")
    @Operation(summary = "保存字典")
    @ApiOperationSupport(order = 20)
    public JsonResult save(DictRegPara para) {
        log.debug("控制器接收参数:{}",para);
        service.save(para);
        return JsonResult.ok();
    }

    @PostMapping("delete/{id}")
    @Operation(summary = "删除字典")
    @ApiOperationSupport(order = 30)
    public JsonResult delete(@PathVariable("id") Long dictId) {
        log.debug("控制器层参数:{}",dictId);
        service.delete(dictId);
        return JsonResult.ok();
    }
}