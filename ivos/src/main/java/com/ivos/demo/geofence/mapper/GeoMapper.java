package com.ivos.demo.geofence.mapper;

import com.ivos.demo.geofence.pojo.dto.GeofenceQuery;
import com.ivos.demo.geofence.pojo.entity.Geofence;
import com.ivos.demo.geofence.pojo.vo.GeofenceVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeoMapper {
    List<GeofenceVo> selectGeofence(GeofenceQuery query);

    void update(Geofence geofence);

    void delete(Long geoId);

    void save(Geofence geofence);
}
