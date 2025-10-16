package com._02vue.demo.geofence.service;

import com._02vue.demo.geofence.pojo.dto.GeofenceQuery;
import com._02vue.demo.geofence.pojo.vo.GeofenceVo;

import java.util.List;

public interface GeofenceService {
    List<GeofenceVo> query(GeofenceQuery query);
}
