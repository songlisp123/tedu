package com._02vue.demo.geofence.mapper;

import com._02vue.demo.geofence.pojo.dto.GeofenceQuery;
import com._02vue.demo.geofence.pojo.entity.Geofence;
import com._02vue.demo.geofence.pojo.vo.GeofenceVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeofenceMapper {
    List<GeofenceVo> query(GeofenceQuery query);

    void update(Geofence geofence);

    void delete(Long geoId);

    void save(Geofence geofence);
}
