package com.ivos.demo.geofence.service.implement;

import com.ivos.demo.geofence.mapper.GeoMapper;
import com.ivos.demo.geofence.pojo.dto.GeofenceQuery;
import com.ivos.demo.geofence.pojo.vo.GeofenceVo;
import com.ivos.demo.geofence.service.Serve;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ServeImplement implements Serve {

    @Autowired
    private GeoMapper mapper;

    @Override
    public List<GeofenceVo> selectGeofence(GeofenceQuery query) {
        log.debug("业务层层面参数:{}",query);
        List<GeofenceVo> response = mapper.selectGeofence(query);
        return response;
    }
}
