package com.ivos.demo.vehicle.vehicleController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "车辆模块")
@RestController
@RequestMapping("/v1/vehicle/")
public class vehicleController {
}
