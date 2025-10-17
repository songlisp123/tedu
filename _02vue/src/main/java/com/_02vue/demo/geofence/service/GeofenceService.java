package com._02vue.demo.geofence.service;

import com._02vue.demo.geofence.pojo.dto.GeofenceQuery;
import com._02vue.demo.geofence.pojo.dto.GeofenceSave;
import com._02vue.demo.geofence.pojo.vo.GeofenceVo;

import java.util.List;

public interface GeofenceService {
    List<GeofenceVo> query(GeofenceQuery query);

    void update(Long geofenceId, String status);

    void delete(Long geoId);

    void save(GeofenceSave save);
}
