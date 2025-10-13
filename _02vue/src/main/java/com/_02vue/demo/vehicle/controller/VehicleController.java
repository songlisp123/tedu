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
    public JsonResult save(@RequestBody RegVehiclePara para)
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
}
