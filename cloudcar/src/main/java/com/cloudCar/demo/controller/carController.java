package com.cloudCar.demo.controller;

import com.cloudCar.demo.base.response.JsonResult;
import com.cloudCar.demo.base.response.StatussCode;
import com.cloudCar.demo.mapper.carMapper;
import com.cloudCar.demo.pojo.DTO.VehicleAddParam;
import com.cloudCar.demo.pojo.DTO.VehicleListQuery;
import com.cloudCar.demo.pojo.entity.Car;
import com.cloudCar.demo.pojo.vo.userVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@Tag(name = "02汽车管理模块")
@RequestMapping("/v1/car/")
public class carController {

    @Autowired
    private carMapper carMapper;

    private static final Logger logger = Logger.getLogger("car");
    @PostMapping("add")
    @Operation(summary = "添加车辆")
    @ApiOperationSupport(order = 100)
    public JsonResult add(
            @RequestBody VehicleAddParam vehicleAddParam,
            HttpSession session)
    {
        log.debug("添加车辆业务");
        logger.info("添加车辆业务");
        userVO userVO = (userVO) session.getAttribute("user");
        if (userVO == null) {
            log.debug("用户未登录");
            logger.warning("用户尚未登录");
            return new JsonResult(StatussCode.NOT_LOGIN);
        }
        Car car = new Car();
        BeanUtils.copyProperties(vehicleAddParam,car);
        car.setCreateTime(new Date());
        car.setUpdateTime(new Date());
        car.setUserId(userVO.getId());
        int i = carMapper.insertCar(car);
        if (i>0) {
            log.debug("添加车辆成功");
            logger.info("添加车辆成功");
            return JsonResult.ok();
        }
        log.debug("发生未知错误，请重试");
        logger.warning("发生未知错误，请重试");
        return new JsonResult(StatussCode.OPERATION_FAILED);
    }

    @PostMapping("login")
    @Operation(summary = "查询车辆")
    @ApiOperationSupport(order = 200)
    public JsonResult login() {
        return JsonResult.ok();
    }

    @GetMapping("list")
    @Operation(summary = "列出车辆")
    @ApiOperationSupport(order = 201)
    @Parameters(value = {
            @Parameter(name = "brand"),
            @Parameter(name = "license"),
            @Parameter(name = "VehicleListQuery",hidden = true),
    })
    public JsonResult list(
            VehicleListQuery vehicleListQuery,
            HttpSession session)
    {
        log.debug("汽车查询服务");
        logger.info("汽车查询服务");
        userVO userVO = (userVO) session.getAttribute("user");
        if (userVO == null) {
            log.debug("用户尚未登陆");
            logger.info("用户尚未登录");
            return new JsonResult(StatussCode.NOT_LOGIN);
        }
        Long userId = userVO.getId();
        List<Car> cars = carMapper.selectAllCar(vehicleListQuery,userId)
                .stream()
                .filter(e->e.getIdDelete()!=1)
                .toList();
        logger.info("查询成功！");
        return JsonResult.ok(cars);
    }

    @PostMapping("del/{id}")
    @Operation(summary = "删除车辆")
    @ApiOperationSupport(order = 400)
    public JsonResult del(
            @PathVariable @Range(min = 1L,max = 500L,message = "参数值必须在1-500之间") Long id,
            HttpSession session) {
        //判断id的范围
        //判断id的
        log.debug("删除id"+id);
        userVO userVO = (userVO) session.getAttribute("user");
        if (userVO==null) {
            logger.warning("用户未登录");
            return new JsonResult(StatussCode.NOT_LOGIN);
        }
        Long userId = userVO.getId();
        int i = carMapper.deleteCarById(id,userId);
        if (i>0) {
            logger.info("汽车删除成功");
            return JsonResult.ok();
        }
        logger.warning("发生未知错误，请重试");
        return new JsonResult(StatussCode.CAR_NOT_EXISTS);
    }
}
