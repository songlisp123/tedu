package com._02vue.demo.geofence.service.impl;

import com._02vue.demo.geofence.mapper.GeofenceMapper;
import com._02vue.demo.geofence.pojo.dto.GeofenceQuery;
import com._02vue.demo.geofence.pojo.vo.GeofenceVo;
import com._02vue.demo.geofence.service.GeofenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GeofenceImplement implements GeofenceService {

    @Autowired
    private GeofenceMapper geofenceMapper;

    @Override
    public List<GeofenceVo> query(GeofenceQuery query) {
        log.debug("业务层参数:{}",query);
        return geofenceMapper.query(query);
    }
}
