package com.ivos.demo.vehicle.vehicleController;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivos.demo.base.response.JsonResult;
import com.ivos.demo.vehicle.pojo.dto.saveVehiclePara;
import com.ivos.demo.vehicle.pojo.dto.vehicleQuery;
import com.ivos.demo.vehicle.pojo.vo.VehicleVo;
import com.ivos.demo.vehicle.vehicleImpl.vehicleService;
import io.micrometer.common.lang.Nullable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "车辆模块")
@RestController
@RequestMapping("/v1/vehicle/")
public class vehicleController {

    @Autowired
    private vehicleService service;

    @GetMapping("query")
    @Operation(summary = "检索车辆请求")
    @ApiOperationSupport(order = 100)
    public JsonResult query(vehicleQuery vehicleQuery) {
        //测试
        log.debug("进入到车辆查询阶段");
        List<VehicleVo> vehicleVos = service.selectCarByLicenseAndBrand(vehicleQuery);
        return JsonResult.ok(vehicleVos);

    }

    @PostMapping("save")
    @Operation(summary = "保存用户车辆")
    @ApiOperationSupport(order = 200)
    public JsonResult save(@RequestBody saveVehiclePara saved) {
        log.debug("控制器接收参数");
        service.save(saved);
        return JsonResult.ok();
    }

    @PostMapping("delete/{id}")
    @Operation(summary = "删除车辆操作")
    @ApiOperationSupport(order = 300)
    public JsonResult delete(@PathVariable("id") Long vehicleId)
    {
        log.debug("控制器：{}",vehicleId);
        service.delete(vehicleId);
        return JsonResult.ok();
    }
}
