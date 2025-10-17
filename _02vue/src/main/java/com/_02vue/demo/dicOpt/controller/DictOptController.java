package com._02vue.demo.dicOpt.controller;

import com._02vue.demo.base.response.JsonResult;
import com._02vue.demo.dicOpt.pojo.dto.DicOptSave;
import com._02vue.demo.dicOpt.pojo.dto.DictOptQuery;
import com._02vue.demo.dicOpt.pojo.vo.DictOptVo;
import com._02vue.demo.dicOpt.service.DictOptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/dictOpt/")
public class DictOptController {

    @Autowired
    private DictOptService service;

    @GetMapping("query")
    public JsonResult query(DictOptQuery query) {
        log.debug("控制器参数:{}",query);
        List<DictOptVo> vos = service.query(query);
        return JsonResult.ok(vos);
    }

    @PostMapping("save")
    public JsonResult save(DicOptSave saved) {
        log.debug("控制器方法：{}",saved);
        service.save(saved);
        return JsonResult.ok();
    }

    @PostMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long optionID) {
        log.debug("控制器参数:{}",optionID);
        service.delete(optionID);
        return JsonResult.ok();
    }
}
