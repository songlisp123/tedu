package com.ivos.demo.geofence.service;

import com.ivos.demo.geofence.pojo.dto.GeofenceQuery;
import com.ivos.demo.geofence.pojo.vo.GeofenceVo;

import java.util.List;

public interface Serve {
    List<GeofenceVo> selectGeofence(GeofenceQuery query);
}
