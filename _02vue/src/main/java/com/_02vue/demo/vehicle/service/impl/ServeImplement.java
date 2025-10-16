package com._02vue.demo.vehicle.service.impl;

import com._02vue.demo.vehicle.mapper.VehicleMapper;
import com._02vue.demo.vehicle.pojo.dto.RegVehiclePara;
import com._02vue.demo.vehicle.pojo.dto.VehicleQuery;
import com._02vue.demo.vehicle.pojo.entity.*;
import com._02vue.demo.vehicle.pojo.vo.VehicleVo;
import com._02vue.demo.vehicle.service.Serve;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ServeImplement implements Serve {

    @Autowired
    private VehicleMapper mapper;

    @Override
    public void save(RegVehiclePara para) {
        log.debug("业务层参数：{}",para);
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(para,vehicle);
        if (vehicle.getId() != null) {
            vehicle.setUpdateTime(new Date());
            mapper.update(vehicle);

        }
        else {
            vehicle.setCreateTime(new Date());
            vehicle.setStatus("1");
            vehicle.setGeofenceBindStatus("0");
            vehicle.setGeofenceId(null);
            mapper.save(vehicle);
        }
    }

    @Override
    public void delete(Long vehicleId) {
        log.debug("业务层参数:{}",vehicleId);
        mapper.delete(vehicleId);
    }

    @Override
    public List<VehicleVo> select(VehicleQuery query) {
        log.debug("查询参数:{}",query);
        List<VehicleVo> cars = mapper.select(query);
        return cars;
    }

    @Override
    public void bind(Long vehicleId, Long geofenceId) {
        log.debug("业务层参数！");
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setGeofenceId(geofenceId);
        vehicle.setGeofenceBindStatus("1");
        vehicle.setUpdateTime(new Date());
        mapper.update(vehicle);
    }

    @Override
    public void unbind(Long vehicleId) {
        log.debug("业务层参数-汽车id号位={}",vehicleId);
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setGeofenceId(null);
        vehicle.setGeofenceBindStatus("0");
        vehicle.setUpdateTime(new Date());
        mapper.unbind(vehicle);
    }

    //    private Battery battery(RegVehiclePara para) {
//        return Arrays.stream(Battery.values())
//                .filter(g->g.getCode().equals(para.getBatteryType()))
//                .findFirst().orElseGet(null);
//    }
//
//    private Color color(RegVehiclePara para) {
//        return Arrays.stream(Color.values())
//                .filter(g->g.getCode().equals(para.getColor()))
//                .findFirst()
//                .orElseGet(null);
//    }
//
//    private Type type(RegVehiclePara para) {
//        return Arrays.stream(Type.values())
//                .filter(g->g.getCode().equals(para.getType()))
//                .findFirst()
//                .orElseGet(null);
//    }
}
