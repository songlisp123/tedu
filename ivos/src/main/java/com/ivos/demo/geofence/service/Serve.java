package com.ivos.demo.geofence.service;

import com.ivos.demo.geofence.pojo.dto.GeofenceQuery;
import com.ivos.demo.geofence.pojo.dto.GeofenceSavePara;
import com.ivos.demo.geofence.pojo.vo.GeofenceVo;

import java.util.List;

public interface Serve {
    List<GeofenceVo> selectGeofence(GeofenceQuery query);

    void updateStatus(Long geoId, String status);

    void delete(Long geoId);

    void save(GeofenceSavePara para);
}
