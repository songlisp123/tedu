package com.ivos.demo.geofence.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivos.demo.base.response.JsonResult;
import com.ivos.demo.geofence.pojo.dto.GeofenceQuery;
import com.ivos.demo.geofence.pojo.vo.GeofenceVo;
import com.ivos.demo.geofence.service.Serve;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
