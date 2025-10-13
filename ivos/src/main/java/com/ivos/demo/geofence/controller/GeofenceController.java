package com.ivos.demo.geofence.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivos.demo.base.response.JsonResult;
import com.ivos.demo.geofence.pojo.dto.GeofenceQuery;
import com.ivos.demo.geofence.pojo.dto.GeofenceSavePara;
import com.ivos.demo.geofence.pojo.vo.GeofenceVo;
import com.ivos.demo.geofence.service.Serve;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "围栏模块")
@RestController
@RequestMapping("/v1/geofence/")
public class GeofenceController {
    @Autowired
    private Serve serve;


    @GetMapping("query")
    @Operation(summary = "查询围栏")
    @ApiOperationSupport(order = 100)
    public JsonResult query(GeofenceQuery query) {
        log.debug("控制器传参：{}",query);
        List<GeofenceVo> response =  serve.selectGeofence(query);
        return JsonResult.ok(response);

    }

    @PostMapping("update/{id}/{status}")
    @Operation(summary = "修改围栏状态")
    @ApiOperationSupport(order = 200)
    public JsonResult update(
            @PathVariable("id") Long geoId,
            @PathVariable("status") String status
    )
    {
        log.debug("控制器方法传参,{},{}",geoId,status);
        serve.updateStatus(geoId,status);
        return JsonResult.ok();
    }

    @PostMapping("delete/{id}")
    @Operation(summary = "删除围栏")
    @ApiOperationSupport(order = 300)
    public JsonResult delete(@PathVariable("id") Long geofenceId)
    {
        log.debug("控制器入参：{}", geofenceId);
        serve.delete(geofenceId);
        return JsonResult.ok();
    }


    @PostMapping("save")
    @Operation(summary = "新增围栏")
    @ApiOperationSupport(order = 400)
    public JsonResult save(@RequestBody GeofenceSavePara para) {
        log.debug("进入到新建围栏阶段");
        serve.save(para);
        return JsonResult.ok();
    }
}
