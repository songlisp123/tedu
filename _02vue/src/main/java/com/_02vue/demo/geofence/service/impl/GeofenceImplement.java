package com._02vue.demo.geofence.service.impl;

import com._02vue.demo.geofence.mapper.GeofenceMapper;
import com._02vue.demo.geofence.pojo.dto.GeofenceQuery;
import com._02vue.demo.geofence.pojo.entity.Geofence;
import com._02vue.demo.geofence.pojo.vo.GeofenceVo;
import com._02vue.demo.geofence.service.GeofenceService;
import com._02vue.demo.vehicle.mapper.VehicleMapper;
import com._02vue.demo.vehicle.pojo.dto.VehicleQuery;
import com._02vue.demo.vehicle.pojo.vo.VehicleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class GeofenceImplement implements GeofenceService {

    @Autowired
    private GeofenceMapper geofenceMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public List<GeofenceVo> query(GeofenceQuery query) {
        log.debug("业务层参数:{}",query);
        return geofenceMapper.query(query);
    }

    @Override
    public void update(Long geofenceId, String status) {
        log.debug("业务层参数：围栏id={},状态机:{}",geofenceId,status);
        Geofence geofence = new Geofence();
        geofence.setId(geofenceId);
        geofence.setStatus(status);
        geofence.setUpdateTime(new Date());
        geofenceMapper.update(geofence);
    }

    @Override
    public void delete(Long geoId) {
        log.debug("业务层参数");
        VehicleQuery vehicleQuery = new VehicleQuery();
        vehicleQuery.setGeofenceId(geoId);
        List<VehicleVo> select = vehicleMapper.select(vehicleQuery);
        if (Objects.equals(select,null) || !select.isEmpty()) {
            throw new RuntimeException("请先移除当前围栏的所有汽车!");
        }
        else  {
            geofenceMapper.delete(geoId);
        }
    }
}
