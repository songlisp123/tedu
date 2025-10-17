package com._02vue.demo.dict.controller;

import com._02vue.demo.base.response.JsonResult;
import com._02vue.demo.dict.pojo.dto.DIctSavePara;
import com._02vue.demo.dict.pojo.dto.DictQuery;
import com._02vue.demo.dict.pojo.vo.DictVo;
import com._02vue.demo.dict.service.DicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/dict/")
public class DictController {

    @Autowired
    private DicService service;

    @GetMapping("query")
    public JsonResult query(DictQuery query) {
        log.debug("控制器参数:{}",query);
        List<DictVo> vos =  service.query(query);
        return JsonResult.ok(vos);
    }

    @PostMapping("save")
    public JsonResult save(DIctSavePara para) {
        log.debug("控制器参数:{}",para);
        service.save(para);
        return JsonResult.ok();
    }

    @PostMapping("update/{id}/{status}")
    public JsonResult updateStatus(
            @PathVariable("id") Long dictId,
            @PathVariable("status") String status
    )
    {
        log.debug("进入到控制器层");
        service.updateStatus(dictId,status);
        return JsonResult.ok();
    }

    @PostMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long dictId) {
        log.debug("进入到控制器层：{}",dictId);
        service.delete(dictId);
        return JsonResult.ok();
    }
}
