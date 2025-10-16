package com._02vue.demo.vehicle.controller;

import com._02vue.demo.base.response.JsonResult;
import com._02vue.demo.vehicle.pojo.dto.RegVehiclePara;
import com._02vue.demo.vehicle.pojo.dto.VehicleQuery;
import com._02vue.demo.vehicle.pojo.vo.VehicleVo;
import com._02vue.demo.vehicle.service.Serve;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/vehicle/")
public class VehicleController {

    @Autowired
    private Serve serve;

    @PostMapping("save")
    public JsonResult save(RegVehiclePara para)
    {
        log.debug("接受参数，{}",para);
        serve.save(para);
        return JsonResult.ok();
    }

    @GetMapping("query")
    public JsonResult query(VehicleQuery query) {
        log.debug("产讯参数{}",query);
        List<VehicleVo> cars = serve.select(query);
        return JsonResult.ok(cars);
    }

    @PostMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long vehicleId)
    {
        log.debug("控制器参数:{}",vehicleId);
        serve.delete(vehicleId);
        return JsonResult.ok();
    }

    @PostMapping("bind/{id}/{geofenceId}")
    public JsonResult bind(@PathVariable("id") Long vehicleId,
                           @PathVariable("geofenceId") Long geofenceId)
    {
        log.debug("汽车id：{},围栏id{}",vehicleId,geofenceId);
        serve.bind(vehicleId,geofenceId);
        return JsonResult.ok();
    }

    @PostMapping("unbind/{id}")
    public JsonResult unbind(@PathVariable("id") Long vehicleId)
    {
        log.debug("当前参数：{}",vehicleId);
        serve.unbind(vehicleId);
        return JsonResult.ok();
    }
}
