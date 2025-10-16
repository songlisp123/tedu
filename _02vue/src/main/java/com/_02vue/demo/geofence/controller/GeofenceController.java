package com._02vue.demo.geofence.controller;

import com._02vue.demo.base.response.JsonResult;
import com._02vue.demo.geofence.pojo.dto.GeofenceQuery;
import com._02vue.demo.geofence.pojo.vo.GeofenceVo;
import com._02vue.demo.geofence.service.GeofenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/geofence/")
public class GeofenceController {

    @Autowired
    private GeofenceService geofenceService;


    @GetMapping("query")
    public JsonResult query(GeofenceQuery query) {
        log.debug("参数{}",query);
        List<GeofenceVo> vos = geofenceService.query(query);
        return JsonResult.ok(vos);
    }

    @PostMapping("update/{id}/{status}")
    public JsonResult update(@PathVariable("id") Long geofenceId , @PathVariable("status") String status) {
        log.debug("控制器层参数:围栏id={}，状态值={}",geofenceId,status);
        geofenceService.update(geofenceId,status);
        return JsonResult.ok();
    }

    @PostMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long geoId) {
        log.debug("控制器层入参：{}",geoId);
        geofenceService.delete(geoId);
        return JsonResult.ok();
    }
}
